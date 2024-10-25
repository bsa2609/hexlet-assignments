package exercise;

import java.io.IOException;
import java.nio.file.OpenOption;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String fileSource1, String fileSource2, String fileResult) {
        CompletableFuture<String> readSourceFile1 = CompletableFuture.supplyAsync(() -> {
            Path pathToFileSourse1 = Paths.get(fileSource1).toAbsolutePath().normalize();
            String data1;

            try {
                data1 = Files.readString(pathToFileSourse1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return data1;

        }).exceptionally(ex -> {
            System.out.println(String.format("File %s reading error: %s", fileSource1, ex.getMessage()));
            return null;
        });

        CompletableFuture<String> readSourceFile2 = CompletableFuture.supplyAsync(() -> {
            Path pathToFileSourse2 = Paths.get(fileSource2).toAbsolutePath().normalize();
            String data2;

            try {
                data2 = Files.readString(pathToFileSourse2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return data2;

        }).exceptionally(ex -> {
            System.out.println(String.format("File %s reading error: %s", fileSource2, ex.getMessage()));
            return null;
        });

        CompletableFuture<String> unionFiles = readSourceFile1.thenCombine(readSourceFile2, (data1, data2) -> {
            String fileResultPath = null;

            if (data1 != null && data2 != null) {
                String allData = data1 + data2;

                Path pathToFileResult = Paths.get(fileResult).toAbsolutePath().normalize();
                try {
                    Files.writeString(pathToFileResult, allData, new StandardOpenOption[]{
                            StandardOpenOption.CREATE,
                            StandardOpenOption.TRUNCATE_EXISTING,
                            StandardOpenOption.WRITE});
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                fileResultPath = pathToFileResult.toString();
            }
            return fileResultPath;

        }).exceptionally(ex -> {
            System.out.println(String.format("File %s writing error: %s", fileResult, ex.getMessage()));
            return null;
        });

        return unionFiles;
    }

    public static CompletableFuture<Long> getDirectorySize(String directory) {
        CompletableFuture<Long> calculateFilesSize = CompletableFuture.supplyAsync(() -> {
            Path startPath = Paths.get(directory).toAbsolutePath().normalize();

            Long filesSize = null;
            try (var paths = Files.walk(startPath, 1)) {
                filesSize = paths
                        .filter(Files::isRegularFile)
                        .map(path -> {
                            long fileSize = 0;
                            try {
                                fileSize = Files.size(path);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                            return fileSize;
                        })
                        .reduce(0L, Long::sum);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return filesSize;
        }).exceptionally(ex -> {
            return 0L;
        });

        return calculateFilesSize;
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = unionFiles(
                "src/main/resources/file1.txt",
                "src/main/resources/file2.txt",
                "src/main/resources/union.txt");

        String message = result.get();
        if (message == null) {
            System.out.println("Union of files don't save");
        } else {
            System.out.println(String.format("Union of files save in %s", message));
        }

        CompletableFuture<Long> size = getDirectorySize("src/test/resources/dir");

        Long filesSize = size.get();
        System.out.println(String.format("Size of all files: %d", filesSize));
        // END
    }
}


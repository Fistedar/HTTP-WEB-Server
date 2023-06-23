package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
public class Main {
  public static void main(String[] args) {
    final var server = new Server();
    server.addHandler("GET", "/", (request, responseStream) -> {
      final var filePath = Path.of(".", "public", "index.html");
        try {
            Files.probeContentType(filePath);
      } catch (IOException e) {
        e.printStackTrace();
      }


        try {
            Files.size(filePath);
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        Files.copy(filePath, responseStream);
      } catch (IOException e) {
        e.printStackTrace();
      }

    });
    server.listen(9999);
  }
}


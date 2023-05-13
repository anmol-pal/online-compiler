package com.compiler.compiler.compilerfiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompilerService {

    String fileBasePath = "";

    private ResponseEntity<String> saveFile(CompilerEntity entityIn, String fileName) {
        // Save code to a file

        try {
            // Create a new FileOutputStream object for the file "example.txt"
            FileOutputStream fos = new FileOutputStream(fileBasePath + fileName);
            fos.write(entityIn.code.getBytes());
            // Close the FileOutputStream
            fos.close();
            System.out.println("File created successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating the file. " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    private ResponseEntity<String> compileFile(CompilerEntity entityIn, String fileName) {
        // Compile code
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(fileBasePath + fileName);

        List<String> options = new ArrayList<>(Arrays.asList("-classpath", System.getProperty("java.class.path")));

        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null,
                compilationUnits);

        if (task.call()) {
            System.out.println("Compilation succeeded");
        } else {
            System.out.println("Compilation failed");
            // send compilation failed message
            for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                System.out.format("\n\nError on line %d in %s\n", diagnostic.getLineNumber(),
                        diagnostic.getSource().toUri());
                System.out.println(diagnostic.getMessage(null) + "\n\n\n");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(diagnostic.getMessage(null));

            }
        }

        try {
            fileManager.close();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    private ResponseEntity<String> runFile(CompilerEntity entityIn, String fileName, String ttydPort) {

        String runCommand = " java " + "Main";
        String command = "ttyd -o -p " + ttydPort + runCommand;
        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command("bash", "-c", command);

        try {
            Process process = processBuilder.start();

            return ResponseEntity.status(HttpStatus.OK).body("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    public ResponseEntity<String> compilerAndExecuteCode(CompilerEntity entityIn) {

        String fileName = "Main." + entityIn.extension;
        String ttydPort;

        ResponseEntity<String> fileSavedFlag = saveFile(entityIn, fileName);
        if (fileSavedFlag.getStatusCode() != HttpStatus.OK) {
            return fileSavedFlag;
        }

        ResponseEntity<String> fileCompiledFlag = compileFile(entityIn, fileName);
        if (fileCompiledFlag.getStatusCode() != HttpStatus.OK) {
            return fileCompiledFlag;
        }

        ttydPort = "8081";

        ResponseEntity<String> runFileFlag = runFile(entityIn, fileName, ttydPort);
        if (runFileFlag.getStatusCode() != HttpStatus.OK) {
            return runFileFlag;
        }
        String jsonResponse = "{\"port\": "+ttydPort+", \"externalURL\": \"localhost\"}";
        try {
            jsonResponse = "{\"port\": "+ttydPort+", \"externalURL\": \"" + InetAddress.getLocalHost().getHostAddress() + "\"}";// System.getenv("EXTERNAL_IP")
            System.out.println("json");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(jsonResponse);
    }
}
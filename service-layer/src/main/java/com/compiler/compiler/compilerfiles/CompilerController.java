package com.compiler.compiler.compilerfiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompilerController {

    @Autowired
    CompilerService compilerService;

    @RequestMapping("/health")
    public String getTest() {
        return "Jai mata di";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/execute")
    public ResponseEntity<String> compileExecute(@RequestBody CompilerEntity entiryIn) {

        return compilerService.compilerAndExecuteCode(entiryIn);

    }
}

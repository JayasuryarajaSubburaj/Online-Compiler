package com.onlinecompiler.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinecompiler.entity.CodeRequestEntity;
import com.onlinecompiler.util.CPlusPlusRunner;
import com.onlinecompiler.util.CRunner;
import com.onlinecompiler.util.JavaRunner;
import com.onlinecompiler.util.PythonRunner;

@RestController
@RequestMapping("/onlinelearning/")
public class CompilerController {
		
	@PostMapping("/execute")
	public String executeCode(@RequestBody CodeRequestEntity request) {
		switch (request.getType()) {
		case "python":
			PythonRunner.runPythonCode(request.getCode());
			break;
		case "c":
			CRunner.runCCode(request.getCode());
			break;
		case "cpp":
			CPlusPlusRunner.runCPlusPlusCode(request.getCode());
			break;
		case "java":
			JavaRunner.runJavaCode(request.getCode());
			break;
		default:
			return "Invalid type!";
		}
		return "Execution finished!";
	}

}

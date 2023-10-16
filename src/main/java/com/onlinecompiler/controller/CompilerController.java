package com.onlinecompiler.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinecompiler.entity.CodeRequestEntity;
import com.onlinecompiler.entity.ResponseEntity;
import com.onlinecompiler.util.CPlusPlusRunner;
import com.onlinecompiler.util.CRunner;
import com.onlinecompiler.util.JavaRunner;
import com.onlinecompiler.util.PythonRunner;

@RestController
@RequestMapping("/onlinelearning/")
public class CompilerController {

	@PostMapping("/execute")
	public ResponseEntity executeCode(@RequestBody CodeRequestEntity request) {
		String output = "";
		switch (request.getType()) {
		case "python":
			output = PythonRunner.runPythonCode(request.getCode());
			break;
		case "c":
			output = CRunner.runCCode(request.getCode());
			break;
		case "cpp":
			output = CPlusPlusRunner.runCPlusPlusCode(request.getCode());
			break;
		case "java":
			output = JavaRunner.runJavaCode(request.getCode());
			break;
		default:
			return new ResponseEntity("Invalid type!", "");
		}
		return new ResponseEntity("Execution finished", output);
	}

}

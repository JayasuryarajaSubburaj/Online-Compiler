package com.onlinecompiler.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinecompiler.entity.CodeRequestEntity;
import com.onlinecompiler.entity.ResponseEntity;
import com.onlinecompiler.service.CPlusPlusRunnerTask;
import com.onlinecompiler.service.CRunnerTask;
import com.onlinecompiler.service.JavaRunnerTask;
import com.onlinecompiler.service.PythonRunnerTask;

@RestController
@RequestMapping("/onlinelearning/")
public class CompilerController {

	@Autowired
	private ThreadPoolExecutor threadPoolExecutor;

	@PostMapping("/execute")
	public ResponseEntity executeCode(@RequestBody CodeRequestEntity request) {

//		String output = "";
//		
//		switch (request.getType()) {
//		case "python":
//			output = PythonRunner.runPythonCode(request.getCode());
//			break;
//		case "c":
//			output = CRunner.runCCode(request.getCode());
//			break;
//		case "cpp":
//			output = CPlusPlusRunner.runCPlusPlusCode(request.getCode());
//			break;
//		case "java":
//			output = JavaRunner.runJavaCode(request.getCode());
//			break;
//		default:
//			return new ResponseEntity("Invalid type!", "");
//		}
//		return new ResponseEntity("Execution finished", output);
//}
		
		Callable<String> task;
		String output = "";

		try {
			switch (request.getType()) {
			case "python":
				task = new PythonRunnerTask(request.getCode());
				output = threadPoolExecutor.submit(task).get();
				break;
			case "java":
                task = new JavaRunnerTask(request.getCode());
                output = threadPoolExecutor.submit(task).get();
                break;
            case "c":
                task = new CRunnerTask(request.getCode());
                output = threadPoolExecutor.submit(task).get();
                break;
            case "cpp":
                task = new CPlusPlusRunnerTask(request.getCode());
                output = threadPoolExecutor.submit(task).get();
                break;
            default:
                return new ResponseEntity("Invalid type!", "");
			}
		} catch (InterruptedException | ExecutionException e) {
			return new ResponseEntity("Error executing code!", "");
		}

		return new ResponseEntity("Execution finished", output);
	}
	
	@GetMapping("/compile")
	public String getMsg() {
		return "hello compiler";
	}
}


package com.sid.gl;


import com.sid.gl.process.BankTransactionAnalyticProcessor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BankRestController {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    @Autowired
    private BankTransactionAnalyticProcessor bankTransactionAnalyticProcessor;

    @GetMapping("/startJob")
    private BatchStatus load() throws Exception {
        Map<String, JobParameter> jobParameterMap=
                new HashMap<>();
        jobParameterMap.put("time",new JobParameter(System.currentTimeMillis()));
        JobParameters parameters =
                new JobParameters(jobParameterMap);
        JobExecution jobExecution = jobLauncher.run(job,parameters);
        while(jobExecution.isRunning()){
            System.out.println("............");
        }
        return jobExecution.getStatus();
    }

    @GetMapping("/analytics")
    public Map<String,Double> analytics(){
        Map<String,Double> map =
                new HashMap<>();
        map.put("totalCredit", bankTransactionAnalyticProcessor.getTotalCredit());
        map.put("total Débit",bankTransactionAnalyticProcessor.getTotalDebit());
        return map;
    }



}

package com.lambdaschool.javaorders.controllers;

import com.lambdaschool.javaorders.models.Agent;
import com.lambdaschool.javaorders.services.AgentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agents")
public class AgentsController
{
    @Autowired
    private AgentServices agentServices;
    //    http://localhost:2019/agents/agent/9
    @GetMapping(value = "/agent/{agentid}", produces = "application/json")
    public ResponseEntity<?> findRestaurantById(@PathVariable
                                                    long agentid) {
        Agent agent = agentServices.findById(agentid);
        return new ResponseEntity<>(agent, HttpStatus.OK);
    }

    //  localhost:2019/agents/unassigned/8
    @DeleteMapping(value = "/unassigned/{agentid}")
    public ResponseEntity<?> deleteAgentWithZeroCustomers(@PathVariable long agentid)
    {
        agentServices.delete(agentid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

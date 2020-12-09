package com.lambdaschool.javaorders.services;


import com.lambdaschool.javaorders.models.Agent;
import com.lambdaschool.javaorders.repositories.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Transactional
@Service(value = "agentservices")
public class AgentServicesImpl implements AgentServices
{
    @Autowired
    private AgentsRepository agentsRepository;

    @Override
    public Agent findById(long id)
    {
        Agent a = agentsRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Restaurant " + id + " Not Found!"));
        return a;
    }

    @Transactional
    @Override
    public Agent save(Agent agent) {
        return agentsRepository.save(agent);
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        Agent deleteAgent = agentsRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Agent " + id + " Not Found"));

        if (deleteAgent.getCustomers().size() == 0) {
            agentsRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Found A Customer For Agent " + id);
        }
    }
}

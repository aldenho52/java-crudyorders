package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Agent;

public interface AgentServices
{
    Agent findById(long id);
    Agent save(Agent agent);
}

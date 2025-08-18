package com.company.bpmcourse.security;

import com.company.bpmcourse.entity.User;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;

@ResourceRole(name = "PizzaProcessActorRole", code = PizzaProcessActorRole.CODE, scope = "UI")
public interface PizzaProcessActorRole {
    String CODE = "pizza-process-actor-role";

    @EntityAttributePolicy(entityClass = User.class, attributes = {"username", "firstName", "lastName"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.READ)
    void user();
}
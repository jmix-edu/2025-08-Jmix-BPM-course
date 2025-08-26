package com.company.bpmcourse.security;

import com.company.bpmcourse.entity.*;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "PizzaProcessActorRole", code = PizzaProcessActorRole.CODE, scope = "UI")
public interface PizzaProcessActorRole {
    String CODE = "pizza-process-actor-role";

    @EntityAttributePolicy(entityClass = User.class, attributes = {"username", "firstName", "lastName"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.READ)
    void user();

    @MenuPolicy(menuIds = "AdvancedTaskListView")
    @ViewPolicy(viewIds = {"AdvancedTaskListView", "ApproverForm", "PizzaEaterForm", "PizzaStartForm", "PlaceOrderForm"})
    void screens();

    @EntityAttributePolicy(entityClass = OrderLine.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = OrderLine.class, actions = EntityPolicyAction.ALL)
    void orderLine();

    @EntityAttributePolicy(entityClass = PizzaItem.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = PizzaItem.class, actions = EntityPolicyAction.ALL)
    void pizzaItem();

    @EntityAttributePolicy(entityClass = PizzaOrder.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = PizzaOrder.class, actions = EntityPolicyAction.ALL)
    void pizzaOrder();

    @EntityAttributePolicy(entityClass = Recipe.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Recipe.class, actions = EntityPolicyAction.ALL)
    void recipe();
}
package org.shurupov.spaceflight.engine.ioc.context;

import static org.shurupov.spaceflight.engine.ioc.strategy.ScopedIoCStrategy.META_KEY;
import static org.shurupov.spaceflight.engine.ioc.strategy.ScopedIoCStrategy.PARENT_SCOPE_KEY;
import static org.shurupov.spaceflight.engine.ioc.strategy.ScopedIoCStrategy.SCOPE_NAME_KEY;

import java.util.Map;

public class SelectParentScope extends Scopeable implements ContextProcessor {

  @Override
  public void process(Map<String, Object> context, Object[] args) {
    Map<String, Object> meta = (Map<String, Object>) context.get(META_KEY);

    Map<String, Object> currentScope = (Map<String, Object>) meta.get(getCurrentScopeThreadLocal(meta).get());

    if (currentScope.containsKey(PARENT_SCOPE_KEY)) {
      Map<String, Object> parentScope = (Map<String, Object>) context.get((String) currentScope.get(PARENT_SCOPE_KEY));
      String parentScopeName = (String) parentScope.get(SCOPE_NAME_KEY);
      getCurrentScopeThreadLocal(meta).set(parentScopeName);
    }
  }
}

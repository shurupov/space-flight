package org.shurupov.spaceflight.engine.ioc.context;

import static org.shurupov.spaceflight.engine.ioc.strategy.ScopedIoCStrategy.META_KEY;
import static org.shurupov.spaceflight.engine.ioc.strategy.ScopedIoCStrategy.SCOPE_NAMES_KEY;

import java.util.List;
import java.util.Map;

public class SelectScope extends Scopeable implements ContextProcessor {

  @Override
  public void process(Map<String, Object> context, Object[] args) {
    String scopeName = (String) args[0];
    Map<String, Object> meta = (Map<String, Object>) context.get(META_KEY);
    List<String> scopes = (List<String>) meta.get(SCOPE_NAMES_KEY);
    if (scopes.contains(scopeName)) {
      getCurrentScopeThreadLocal(meta).set(scopeName);
    }
  }
}

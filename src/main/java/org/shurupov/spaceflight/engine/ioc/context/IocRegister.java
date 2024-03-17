package org.shurupov.spaceflight.engine.ioc.context;

import java.util.Map;

public class IocRegister implements ContextProcessor {

  @Override
  public void process(Map<String, Object> context, Object[] args) {
    String key = (String) args[0];
    context.put(key, args[1]);
  }
}

package org.shurupov.spaceflight.engine.ioc.context;

import java.util.Map;

public interface ContextProcessor {
  void process(Map<String, Object> context, Object[] args);
}

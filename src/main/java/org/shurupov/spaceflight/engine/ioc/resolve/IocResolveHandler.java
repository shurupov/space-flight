package org.shurupov.spaceflight.engine.ioc.resolve;

import java.util.Map;

public interface IocResolveHandler<V, R> {
  boolean canHandle(Object value);

  R resolve(Map<String, Object> context, V value, Object[] args);

}

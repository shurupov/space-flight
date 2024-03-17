package org.shurupov.spaceflight.engine.ioc.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.shurupov.spaceflight.engine.ioc.resolve.IocResolveHandler;

public class SimpleIoCStrategy extends AbstractIoCStrategy {

  public SimpleIoCStrategy(Map<String, Object> initContext, List<IocResolveHandler> resolveHandlers) {
    super(new HashMap<>(initContext), resolveHandlers);
  }

  protected Object get(String key) {
    return context.get(key);
  }

  @Override
  protected Map<String, Object> getContext() {
    return context;
  }

}

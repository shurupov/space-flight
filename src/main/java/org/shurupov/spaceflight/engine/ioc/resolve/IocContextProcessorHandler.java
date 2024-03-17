package org.shurupov.spaceflight.engine.ioc.resolve;

import java.util.Map;
import org.shurupov.spaceflight.engine.ioc.context.ContextProcessor;

public class IocContextProcessorHandler implements IocResolveHandler<ContextProcessor, Boolean> {

  @Override
  public boolean canHandle(Object value) {
    return value instanceof ContextProcessor;
  }

  @Override
  public Boolean resolve(Map<String, Object> context, ContextProcessor contextProcessor, Object[] args) {
    try {
      contextProcessor.process(context, args);
      return Boolean.TRUE;
    } catch (Throwable e) {
      return Boolean.FALSE;
    }
  }
}

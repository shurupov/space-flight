package org.shurupov.spaceflight.engine.ioc.strategy;

public interface IoCStrategy {
  <T> T resolve(String key, Object ...args);
}

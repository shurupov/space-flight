package org.shurupov.spaceflight.engine.ioc;

import java.util.List;
import java.util.Map;
import org.shurupov.spaceflight.engine.ioc.context.CreateScope;
import org.shurupov.spaceflight.engine.ioc.context.IocRegister;
import org.shurupov.spaceflight.engine.ioc.context.IocUnregister;
import org.shurupov.spaceflight.engine.ioc.context.RemoveScope;
import org.shurupov.spaceflight.engine.ioc.context.SelectParentScope;
import org.shurupov.spaceflight.engine.ioc.context.SelectScope;
import org.shurupov.spaceflight.engine.ioc.resolve.IocConsumerResolver;
import org.shurupov.spaceflight.engine.ioc.resolve.IocContextFunctionResolver;
import org.shurupov.spaceflight.engine.ioc.resolve.IocContextProcessorHandler;
import org.shurupov.spaceflight.engine.ioc.resolve.IocFunctionResolver;
import org.shurupov.spaceflight.engine.ioc.resolve.IocSupplierResolver;
import org.shurupov.spaceflight.engine.ioc.strategy.IoCStrategy;
import org.shurupov.spaceflight.engine.ioc.strategy.ScopedIoCStrategy;
import org.shurupov.spaceflight.engine.ioc.strategy.SimpleIoCStrategy;

public class IoCFactory {

  public static IoC simple() {
    return new IoC(simpleStrategy());
  }

  public static IoC scoped() {
    return new IoC(scopedStrategy());
  }

  public static IoCStrategy simpleStrategy() {
    IoCStrategy strategy = new SimpleIoCStrategy(
        Map.of("IoC.Register", new IocRegister()),
        List.of(
            new IocContextProcessorHandler(),
            new IocFunctionResolver<>(),
            new IocContextFunctionResolver<>(),
            new IocSupplierResolver<>(),
            new IocConsumerResolver()
        )
    );
    strategy.resolve("IoC.Register", "IoC.Unregister", new IocUnregister());
    return strategy;
  }

  public static IoCStrategy scopedStrategy() {
    IoCStrategy strategy = new ScopedIoCStrategy(
        Map.of("IoC.Register", new IocRegister()),
        List.of(
            new IocContextProcessorHandler(),
            new IocFunctionResolver<>(),
            new IocContextFunctionResolver<>(),
            new IocSupplierResolver<>(),
            new IocConsumerResolver()
        )
    );
    strategy.resolve("IoC.Register", "Scope.Add", new CreateScope());
    strategy.resolve("IoC.Register", "Scope.Select", new SelectScope());
    strategy.resolve("IoC.Register", "Scope.Select.Parent", new SelectParentScope());
    strategy.resolve("IoC.Register", "Scope.Remove", new RemoveScope());
    return strategy;
  }
}

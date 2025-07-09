# Demo Banking App – Access Control Showcase

This is a demo banking application designed to demonstrate advanced access control techniques using Spring Security.
It is based on the following tutorial: https://www.youtube.com/watch?v=9eoi1TViceM

## 🔐 Features

- **Method-Level Security**
  - Enabled via `@EnableMethodSecurity` on the `AccountService` class.
  - Enforces access control at the method level using annotations like `@PreAuthorize`.

- **Field-Level Security**
  - Demonstrated with the `AccountDTO` class.
  - Secured with `@AuthorizeReturnObject` to enforce access checks on specific object fields.

- **Custom Annotations**
  - Several custom annotations are included to simplify access control logic and make the code cleaner.

## 🧠 Tip for Advanced Authorization Logic

The current authorization logic:

```java
@PreAuthorize("#toSave?.owner == authentication?.name")
```
can be abstracted into a Spring bean and written in plain Java. This makes the logic easier to manage, especially as it becomes more complex. This technique is discussed at the end of the reference video.

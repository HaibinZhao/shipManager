## Enabling automatic build in IDEA

1. Open the Settings --> Build-Execution-Deployment --> Compiler and enable :

Build Project Automatically.
2. Update the value of `compiler.automake.allow.when.app.running`

press ctrl+shift+A and search for the registry. In the registry, enable :

```bash
compiler.automake.allow.when.app.running
```
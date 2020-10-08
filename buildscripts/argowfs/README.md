1. Connect to your namespace
2. create a service account with required role to run argo workflow and name it `argowf-svcacc`
3. Run the below command.
```
argo submit perf-run-wf.yaml --serviceaccount argowf-svcacc --watch
```
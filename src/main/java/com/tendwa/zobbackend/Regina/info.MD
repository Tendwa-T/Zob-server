# Service Discovery and Registry Service
> A.K.A 'Regina'

Regina is the system's registry service. Regina is in-charge of calling out and finding the services that are alive and
registering them.

## Functionality

Regina's core functionality includes but is not limited to:
* Service Registry - Add a service to the database given proper params
* Service Discovery - Call out to the service to get service Level health
* Add Endpoint - Provide functionality to add an endpoint
* Update Service Status - Update service status (Does not check, just updates)
* Update Endpoint Status - Update endpoint status (Does not check, just updates)
* List all services


_Think of Regina like the Class-list_
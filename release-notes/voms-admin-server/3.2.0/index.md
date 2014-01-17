---
layout: default
title: VOMS Admin server v. 3.2.0

rfcs: 
    - id: VOMS-260
      title: VOMS Admin cannot handle certificate request for certificates with different CAs and the same subject
    - id: VOMS-259
      title: EMI-3 VOMS-Admin does not publish GLUE2EndpointStartTime
    - id: VOMS-257
      title: VOMS Admin should keep in database the date of last membership expiration warning notification sent

features:
    - id: VOMS-266
      title: Hierarchical notification dispatching in VOMS Admin
    - id: VOMS-351
      title: Request log visible on VOMS Admin webapplication
---

# VOMS Admin server v. 3.2.0

This release provides several bug fixes and improvements for VOMS Admin server.
In particular:

- VOMS Admin now supports **Group managers**, a mechanism which allow the hierarchical dispatching
of the notification resulting from user VO membership and group membership requests.

- A **Request log** section has been added to the VOMS Admin web application. The log shows information
about requests handled (approval time, who approved the request etc.)

The updated VOMS Admin user guide describing the new features can be found [here][voms-admin-guide].

### Bug fixes

{% include list-rfcs.liquid %}

### New features

{% include list-features.liquid %}

### Installation and configuration

Upgrading to this version requires an upgrade of the database and a reconfiguration depending on the version of VOMS admin which is being upgraded.

Follow the instructions in the VOMS [System Administrator Guide]({{site.baseurl}}/documentation/sysadmin-guide).

| Upgrade from | Actions required |
|:------------:|:----------------:|
| EMI-3 (v. 3.1.0) | <span class="label label-important">db upgrade</span> |
| EMI-2 (v. 2.7.0) | <span class="label label-important">db upgrade</span> <span class="label label-info">reconfiguration</span> |

### Known issues

#### [The database upgrade fails if the VOMS database was created with VOMS Admin version 2.5.3][known-issue]

**Workaround**: change manually the database version number in the VOMS
database for all VOs being upgraded, issuing the following command  *before* 
starting the upgrade procedure:

```
update version set admin_version = '2.5.5';
```
[voms-website]: http://italiangrid.github.io/voms
[voms-admin-guide]: {{site.baseurl}}/documentation/voms-admin-guide/3.2.0
[known-issue]: https://ggus.eu/ws/ticket_info.php?ticket=100262

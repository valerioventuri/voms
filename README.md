# VOMS 3.0

WARNING: the following documentation is work in progress and is not reliable, do not read it.

## Basic Concepts

### Attribute Certificates

An Attribute Certiﬁcate (AC for short) is a PKI container, deﬁned in [RFC 3281](http://www.ietf.org/rfc/rfc3281.txt), 
capable of containing a set of attributes tied to a speciﬁc identity. It is the system used by VOMS to issue 
its attributes.

### Groups, roles, and generic attributes

Members of a Virtual Organization can be organized into groups. These groups can then be directly
represented as voms groups. Groups are organized in a hierarchical tree, where each group may have
zero, one or more subgroups, with no limitation on tree depth. The root of the tree is ﬁxed, and is the VO
itself. A group name contains the representation of the path leading to it from the root. For example, if a
user were member of the subgroup bologna of the group italian in the VO test, the group name as
represented by VOMS will be /test/italian/bologna.

__Groups__ There are no effective limits on the length of a group name, excepts those enforced by the
underlying DB in which the name is stored. However, only alphanumeric characters plus ’-’, ’_’ and
’.’ are allowed in group name. Finally, membership in a subgroup requires membership in its parent group. From this it should be
evident that all users must at least be members of the root group.

__Roles__ Not all members of a group are necessarily equal, but some may occasionally have some special
rights that, while not always needed, may indeed be necessary for the execution of special tasks.
A simple example of this is the softwaremanager or sgm role that corresponds to the right of
installing software on grid nodes. This need is represented by the VOMS Roles. A VOMS Role is always associated to a speciﬁc
group. E.g, holding the role sgm in the /test/italian group is a different thing than holding the
same role in the /test group. Restriction on role names are the same as restriction on group names. Furthermore, Role names
starting with VOMS are reserved for use by VOMS itself. Finally, the special name NULL may indicate
that no speciﬁc role is held.

__Generic Attributes__ Finally, it should be noted that not all the charateristics of a user can be represented
by a combination of groups and roles. For example, consider the need for registering the guarantor
of a user. For these cases, generic attributes are used. They consist of triple (name, value, qualiﬁer), with the
qualiﬁer optional. As an example, the guarantor case above could be represented by the following
tuple: (guarantor, “Guarantor Smith”)
There are no hardcoded limits on the number, length and character usable in generic attributes.

### Fully Qualified Attribute Names (FQANs)

A Fully Qualiﬁed Attribute Name (FQAN for short) is a compact way to represent a user’s membership in
a group, along with its role holdership, if any.
Its general syntax would be: <groupname> [/Role=<rolename>]
For example, belonging to the group /test/italian may be represented by the following FQAN:
/test/italian
while holding the role sgm in the same group will be represented by the following FQAN:
/test/italian/Role=sgm

## Installation

Pointers to Last stable build, SL5 and SL6 packages, yum repositories.

_We used to have voms_install_db here. Is it going to still be supported?_

## Configuration

_We Have not decide yet where the configuration file is going to be, and it might be different for packages vs
relocatable tarball. Firs thing you will say where the documentation can be found._

The configuration file contains an extensive description for each of the configuration field, and indicates which is
the default value for each. Change it according to your environment and needs.

### Configure logging

_This will substantially change._

## Starting and stopping the service

Not yet defined.

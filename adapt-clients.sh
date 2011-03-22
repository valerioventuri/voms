#!/bin/sh
dir=$1
action=$2
version=$3

if test "x$action" = "xpre"; then
# Use pdflatex
sed -e 's!^\(USE_PDFLATEX *= *\)NO!\1YES!' -i src/api/ccapi/Makefile.am

# Touch to avoid rerunning bison and flex
touch -r src/utils/vomsfake.y src/utils/vomsparser.h
touch -r src/utils/vomsfake.y src/utils/vomsparser.c
touch -r src/utils/vomsfake.y src/utils/lex.yy.c

touch -r src/sslutils/namespaces.l src/sslutils/lex.namespaces.c
touch -r src/sslutils/namespaces.y src/sslutils/namespaces.c
touch -r src/sslutils/namespaces.y src/sslutils/namespaces.h

touch -r src/sslutils/signing_policy.l src/sslutils/lex.signing.c
touch -r src/sslutils/signing_policy.y src/sslutils/signing_policy.c
touch -r src/sslutils/signing_policy.y src/sslutils/signing_policy.h

# rebootstrap
./autogen.sh
fi

if test "x$action" = "xpost"; then

rm -rf $dir/usr/lib
rm -rf $dir/usr/lib64
rm -rf $dir/usr/libexec
rm -rf $dir/usr/sbin
rm -rf $dir/usr/share/man/man8
rm -rf $dir/include

mkdir -p $dir/usr/share/voms-clients-$version
install -m 644 -p LICENSE AUTHORS $dir/usr/share/voms-clients-$version

fi

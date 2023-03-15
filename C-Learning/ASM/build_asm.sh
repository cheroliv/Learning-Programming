#!/bin/bash

SOURCE=$1
PROG=$2

# Compiling
nasm -f elf64 ${SOURCE}

# Linking
ld -m elf_x86_64 ${SOURCE%.asm}.o -o ./${PROG}

# Cleaning
rm *.o

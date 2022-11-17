/*
 ===============================
 Name         : hash_functions.c
 Author       : Charles T.
 ===============================
*/

unsigned int        djb_hash(const char* str, unsigned int length)
{
    unsigned int    hash = 5381;
    
    for (unsigned int i = 0; i < length; ++str, ++i) {
        hash = ((hash << 5) + hash) + (*str);
    }
    return hash;
}

unsigned int      elf_hash(const char* str, unsigned int length)
{
    unsigned int   hash = 0;
    unsigned int   x    = 0;

    for (unsigned int i = 0; i < length; ++str, ++i) {
        hash = (hash << 4) + (*str);
        if ((x = hash & 0xF0000000L) != 0) {
            hash ^= (x >> 24);
        }
        hash &= ~x;
    }
    return hash;
}

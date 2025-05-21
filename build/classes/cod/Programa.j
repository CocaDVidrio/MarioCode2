.class public Programa
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
    .limit stack 10
    .limit locals 10

    ldc 3
    istore 0
    ldc 5
    istore 1
    ldc 3
    invokestatic Game/agacharse(I)V
    invokestatic Game/mushroom()V
    iload 0
    iload 1
    if_icmpge L0
L0:
    ldc2_w 1.5
    dstore 2
    ldc 0
    istore 3
L1:
    iload 3
    ldc 5
    if_icmpge L2
    iinc 3 1
    goto L1
L2:
    ldc 1
    istore 4
    ldc 2
    istore 4
    ldc 3
    istore 4
    return
.end method

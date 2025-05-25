.class public Programa
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
    .limit stack 10
    .limit locals 10

Label0:
    ldc 3
    istore 1
    ldc 5
    istore 2
    ldc 3
    invokestatic Programa/agacharse(I)V
    invokestatic Programa/mushroom()V
    iload 1
    iload 2
    if_icmpge L0
L0:
    ldc2_w 1.5
    dstore 3
    ldc 0
    istore 4
L1:
    iload 4
    ldc 5
    if_icmpge L2
    iinc 4 1
    goto L1
L2:
    ldc 1
    istore 5
    ldc 2
    istore 5
    ldc 3
    istore 5
    return
Label1:
    .var 1 is a I from Label0 to Label1
    .var 2 is b I from Label0 to Label1
    .var 3 is c D from Label0 to Label1
    .var 4 is i I from Label0 to Label1
    .var 5 is opcion I from Label0 to Label1
.end method

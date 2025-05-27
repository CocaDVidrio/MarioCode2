.class public Programa
.super java/lang/Object

.method public static main([Ljava/lang/String;)V
    .limit stack 10
    .limit locals 10

Label0:
    ldc 1
    invokestatic Programa/mover(I)V
L0:
    ldc 1
    invokestatic Programa/mover(I)V
    ldc 1
    invokestatic Programa/saltar(I)V
    ldc 1
    invokestatic Programa/mover(I)V
    return
Label1:
.end method

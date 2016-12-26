make工具可以在必要时重新编译所有受改动影响的源文件
make命令不仅仅用于编译程序，无论何时，当需要通过多个输入文件来生成输出文件时，你都可以利用它来完成任务。它的其他用法还包括文档处理。

make命令选项
-k：它让make命令在发现错误时仍然继续执行，而不是在检测第一个错误时就停下来。它可以利用这个选项在一次操作中发现所有未编译成功的源文件
-n：它让make命令输出将要执行的操作步骤，而不真正执行这些操作
-f <filename>：它告诉make命令将哪个文件作为makefile文件。如果无此选项，默认在当前目录查找makefile，如果不存在，则寻找Makefile

依赖关系
目标:（空格/制表符）依赖项
myapp: main.o 2.o
main.o: main.c a.h
2.o: 2.c a.h

如果想一次创建多个文件，可以利用伪目标all。如
all: myapp myapp.1
如果未指定一个all目标，则make命令将只创建它在文件makefile中找到的第一个目标

规则：
	规则定义了目标的创建方式。规则所在的行必须以制表符tab开头，用空格不行。另外，如果makefile文件中的某行以空格结尾，也可能导致make命令执行失败。

宏与注释
宏通过变量名，比如MAR=value定义，通过$(MAR)或${MAR}调用，有的版本也接收$MAR.默认的特殊宏有
$?	当前目标所依赖的文件列表中比当前目标文件还要新的文件
$@	当前目标的名字
$<	当前依赖文件的名字
$*	不包括后缀名的当前依赖文件的名字
注释用#作用一行

多目标例子：
all: myapp
#多目标时，输入make，会执行all里面声明的目标，不然默认生成第一个目标。但是可以通过make clean这样执行不同目标。
# which compiler
CC = gcc
#where to install
INSTDIR = /usr/local/bin
myapp: main.o 2.o
	$(CC) -o myapp main.o 2.o
main.: main.c a.h
	$(CC) -c main.c
clean:
	-rm main.o 2.o   
#-开头，让make命令忽略rm命令执行的结果，及时由于目标不存在而导致rm命令返回错误，命令make clean也会成功
#clean：后面是空的，表明总认为是过时的，永远能执行
install: myapp
	@cp myapp $(INSTDIR);\
	chmod a+x $(INSTDIR)/myapp;
#@表示make执行这些指令不在标准输出显示。
#make命令在执行规则时会调用一个shell，并针对每个规则使用一个新shell，所以必须在每行代码结尾加上\，让所有shell脚本命令在逻辑上处于同一行，作为一个整体传递给一个shell执行。


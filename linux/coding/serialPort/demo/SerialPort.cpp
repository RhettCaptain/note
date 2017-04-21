#include "SerialPort.h"

SerialPort::SerialPort(const char* portName)
{
	port = portName;
}

int SerialPort::openPort()
{
	//O_RDWR可读可写，
	//O_NOCTTY告诉UNIX这个程序不会成为这个端口上的“控制终端”
	//O_NDELAY,这个程序并不关心DCD信号线的状态——也就是不关心端口另一端是否已经连接.如果不指定这个标志的话,除非DCD信号线上有space电压否则这个程序会一直睡眠
	fd = open(port,O_RDWR | O_NOCTTY | O_NDELAY);
	if(-1 == fd)
	{
		perror("can't open device!\n");
		return(-1);
	}
	return 0;
}

int SerialPort::closePort()
{
	if(-1 == fd)
	{
		perror("no open device!\n");
		return(-1);
	}
	close(fd);
	return 0;
}

int SerialPort::setPort(int baud,int databits,int stopbits,char parity)
{
	//串口的设置主要是设置struct termios结构体的各成员值.
	struct termios options;
	//与串口操作相关的最重要的两个POSIX函数可能就是tcgetattr(3)和tcsetattr(3).顾名思义,这两个函数分别用来取得设设置终端的属性
	//旧的编程接口将波特率存储在上表所示的c_cflag成员中,而新的接口实装则提供了c_ispeed和c_ospeed成员来保存实际波特率的值
	if(tcgetattr(fd,&options)!=0)
	{
		perror("can't get current option\n");
		return(-1);
	}
	switch(baud)
	{
	case 9600:
		cfsetispeed(&options,B9600);
		cfsetospeed(&options,B9600);
		break;
	case 19200:
		cfsetispeed(&options,B19200);
		cfsetospeed(&options,B19200);
		break;
	case 115200:
		cfsetispeed(&options,B115200);
		cfsetospeed(&options,B115200);
		break;
	default:
		break;	
	}
	//c_cflag控制选项,设置波特率，数据位，校验位，停止位等
	//CLOCAL,CREAD.这两个选项可以保证你的程序不会变成端口的所有者,而端口所有者必须去处理发散性作业控制和挂断信号,同时还保证了串行接口驱动会读取过来的数据字节.
	options.c_cflag |= (CLOCAL|CREAD);
	
	options.c_cflag &= ~CSIZE;
	switch(databits)
	{
	case 7:
		options.c_cflag |= CS7;
		break;
	case 8:
		options.c_cflag |= CS8;
		break;
	default:
		fprintf(stderr,"Unsupported data size\n");
		return(-1);
	} 
	
	switch(parity)
	{
	case 'n':
	case 'N':
		options.c_cflag &= ~PARENB;
		options.c_iflag &= ~INPCK;
		break;
	case 'o':
	case 'O':
		options.c_cflag |= (PARODD | PARENB);
		options.c_iflag |= INPCK;
		break;
	case 'e':
	case 'E':
		options.c_cflag |= PARENB;
		options.c_cflag &= ~PARODD;
		options.c_iflag |= INPCK;
		break;
	case 's':
	case 'S':
		options.c_cflag &= ~PARENB;
		options.c_cflag &= ~CSTOPB;
		break;
	default:
		fprintf(stderr,"Unsipported parity\n");
		return(-1);
	}

	switch(stopbits)
	{
	case 1:
		options.c_cflag &= ~CSTOPB;
		break;
	case 2:
		options.c_cflag |= CSTOPB;
		break;
	default:
		fprintf(stderr,"Unsupported stop bits\n");
		return(-1);
	}

	if(parity != 'n')
	{
		options.c_iflag |= INPCK;
	}
	tcflush(fd,TCIOFLUSH);
	//c_lflag行选项，可以控制串口驱动怎样控制输入字符.通常,你可能需要通过c_lflag成员来设置经典输入和原始输入模式
	//经典输入是以面向行设计的.在经典输入模式中输入字符会被放入一个缓冲之中,这样可以以与用户交互的方式编辑缓冲的内容,直到收到CR(carriage return)或者LF(line feed)字符.
//选择使用经典输入模式的时候,你通常需要选择ICANON,ECHO和ECHOE选项:
	//原始输入根本不会被处理.输入字符只是被原封不动的接收.一般情况中,如果要使用原始输入模式,程序中需要去掉ICANON,ECHO,ECHOE和ISIG选项:
	options.c_lflag &= ~(ICANON | ECHO | ECHOE);		//raw mode:input immediately, no echo
	//原始输出方式可以通过在c_oflag中重置OPOST选项来选择：
	options.c_oflag &= ~OPOST;		//output immediately without buff
	//UNIX串口驱动提供了设置字符和包超时的能力.数组c_cc中有两个元素可以用来设置超时:VMIN和VTIME.
	options.c_cc[VTIME]=50;		//mostly wait 5s for reading in block mode
	options.c_cc[VMIN]=0;
	//TCSANOW	Make changes now without waiting for data to complete
	if(tcsetattr(fd,TCSANOW,&options)!=0)
	{
		perror("can't set new options\n");
		return(-1);
	}
	return(0);
}

void SerialPort::setBlock(bool on)
{
	int flags;
	if(on)
	{
		flags = fcntl(fd,F_GETFL,0);
		flags &= ~O_NONBLOCK;
		fcntl(fd,F_SETFL,flags);
	}
	else
	{
		flags = fcntl(fd,F_GETFL,0);
		flags |= O_NONBLOCK;
		fcntl(fd,F_SETFL,flags);
	}
}

uint SerialPort::crc16_modbus(uchar *p, int len)
{
	char i;
	int j;
	uint crc=0xffff;
	
	for(j=0;j<len;j++)
	{
		crc^=(*p);
		p++;
		for(i=8;i!=0;i--)
		{
			if(crc&1)
			{
				crc>>=1;
				crc^=0xa001;
			}
			else
			{
				crc>>=1;
			}
		}
	}
	return crc;
}

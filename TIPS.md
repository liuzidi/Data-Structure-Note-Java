1.查看构造器的参数快捷键设置：
1.1Ctrl+shift+空格；
1.2.View中的parameter info；
1.3.修改File-setting-keymap中的parameter info快捷键；

2.char字符转int快捷方式：
char a ='5';
 int b = a - '0' ; // b = 5

3.判断越界的方法
int newRes = res * 10 + t;
if ((newRes - t) / 10 != res)//判断越界

long num;
if((int)num == num)//判断越界

4.char字符（字母）在26位中的排序：
int count[] = new int[26];
count[chars[i] - 'a']++;

5.涉及到同一个数组互相比较：
排序
哈希表
双指针
位运算
涉及到字母的哈希表，采用int[26]的哈希表

6.String 拼接和equals方法非常耗内存和效率

7.涉及到变化的String字符串，用StringBuffer效率能够明显提升

8.ArrayList排序：Collections.sort(ArrayList对象);

9.递归可以实现链表的倒序

10.equals方法非常消耗内存，因此慎用String的equals和ArrayList的Integer 的equals方法

11.判断是否有环，可以用快慢指针，就像两个运动员一快一满，如果有环，则后续必相遇；

12.middle类型的题可以用start < end 作为终止条件
分成两个区域left - middle，middle+ 1 - right

13.middle = left + (right - left) / 2;用来防止start + end 越界的情况

14.动态规划问只能解决
14.1.拿走商品的一部分，即商品是不连续的，可以量化的
14.2.只能解决仅当每个子问题都是离散的，即不依赖于其他子问题时，动态规划才起作用

15.可以用max（a，b）在一定场合中替代if(a>b){}的场合，
在一个循环中
int max = dp[0];
    for (int i = 1; i < length; i++) {
        //转移公式
        dp[i] = Math.max(dp[i - 1], 0) + num[i];
        //记录最大值
        max = Math.max(max, dp[i]);
    }
相当于求出dp[]数组的最大值，返回给max

16.寻找一个质数，可以利用Math.sqrt

17.位运算：求无符号int类型的数据转化为32位后的1的数量统计
17.1 这个是最常见的，每次消去最右边的1，直到消完为止
public int hammingWeight(int n) {
    int count = 0;
    while (n != 0) {
        n &= n - 1;//最右边的1会退为0，可能会影响该位后一位0变为1，但是&运算没有影响
        count++;
    }
    return count;
}

17.2 把n往右移32次，每次都和1进行与运算
public int hammingWeight(int n) {
    int count = 0;
        for(int i = 0; i < 32; i++){
            if((res & 1) == 1){
                count++;
                
            }
            res >>>= 1;
        }
        return count;
}

18.ArrayList的get（int index）方法类似于数组的下标

19.差缺少东西就用位运算的异或方法

20.类加载时，变量比short小，保存在方法区，比short大的保存在运行时常量池中。

21.树的概念：
二叉树：
最多两个子树，分左子树和右子树

22.涉及到多次字符串的添加使用StringBuilder效率可以提升很多

23.： 最小栈：最小的出现多次，需要push多次，第二小的数字在第一小数字出现之前一定是最小的，但是若在第一数字出现之后，不过是一个普通的数字

24.自动装箱和自动拆箱的场合：
25.，无进位和 与 异或运算 规律相同，进位 和 与运算 规律相同（并需左移一位）。因此，无进位和 nn 与进位 cc 的计算公式如下；

26.select （要显示的列） 
from （表1，表2）（x * x的合成表格）
where + 限制条件   //分组前的筛选
group by （ 不重复的列字段）按照不同列字段进行分组，常用于sum
having      // 分组后的筛选
Order by（排序方式）desc asc
limit（显示头几条）

27.当索引的最大数量大于值的最大值时，如在一个长度为 n 的数组 nums 里的所有数字都在 0 ~ n-1 的范围内 。可以考虑原地交换法来寻找出重复的元素。
遍历数组 numsnums ，设索引初始值为 i = 0 :

若 nums[i] = inums[i]=i ： 说明此数字已在对应索引位置，无需交换，因此跳过；
若 nums[nums[i]] = nums[i]nums[nums[i]]=nums[i] ： 代表索引 nums[i]nums[i] 处和索引 ii 处的元素值都为 nums[i]nums[i] ，即找到一组重复值，返回此值 nums[i]nums[i] ；
否则： 交换索引为 i和 nums[i]nums[i] 的元素值，将此数字交换至对应索引位置。
若遍历完毕尚未返回，则返回 -1 。

注：保证每次操作都能有一个确认的索引对应到对应的值。

解决问题：
1.自动装箱的场合
2.值传递和引用传递在java中的应用
3.list等接口和多态化的实现

1.自动装箱的场合：
	1.1互相转换的方法：
	Integer aInteger = Integer.valueOf( int aInt)；
	int aInt = aInteger.intValue();
	记忆方法：Integer由于是对象，所以才可以调用方法，可以采用.intValue的方法
	valueOf适用于基本类型转引用类型的场合，如  String 变量名 = String.valueOf（基本类型）

	1.2 赋值过程中，左右分别是Integer和int的时候
	在赋值过程中，Integer = int 或者 int = Integer的语句都可以发生自动装箱
	1.3 return 过程中，如果需要返回Integer，可以返回int类型，此时会发生自动装箱，反之亦然
	1.4 函数参数中需要Integer类型时，可以用int类型的参数来替代，也会发生自动装箱；
	1.5 Integer 和 int类型直接比较大小，比如 ==  < > 等等
	值得注意的是Integer和Integer不能直接互相比较，一般用equals方法，因为如果超出了-128 ~ 127，Integer将不会调用常量池里的cache，而会new一个Integer对象
	 
	1.6 不会发生拆箱或者装箱的过程：
	数组转化，list转数组，两个Integer互相比较大小（用equals）

2.值传递和引用传递的java中的理解
对于基本类型 num ，赋值运算符会直接改变变量的值，原来的值被覆盖掉。
对于引用类型 str，赋值运算符会改变引用中所保存的地址，原来的地址被覆盖掉。但是原来的对象不会被改变。

第一个例子：基本类型
void foo(int value) {
    value = 100;
}
foo(num); // num 没有被改变

第二个例子：没有提供改变自身方法的引用类型
void foo(String text) {
    text = "windows";
}
foo(str); // str 也没有被改变

第三个例子：提供了改变自身方法的引用类型
StringBuilder sb = new StringBuilder("iphone");
void foo(StringBuilder builder) {
    builder.append("4");
}
foo(sb); // sb 被改变了，变成了"iphone4"。

第四个例子：提供了改变自身方法的引用类型，但是不使用，而是使用赋值运算符。
StringBuilder sb = new StringBuilder("iphone");
void foo(StringBuilder builder) {
    builder = new StringBuilder("ipad");
}
foo(sb); // sb 没有被改变，还是 "iphone"。
方法中如果有引用类型的变量，只要不让其直接new一个堆内存，就会脱离原来的变量控制范围。

3.
成员变量：编译看左边（父类），运行看左边（父类）
成员方法：编译看左边（父类），运行看右边（子类），动态绑定
静态方法：编译看左边（父类），运行看左边（父类），静态和类相关，算不上重写，所有访问还是左边的
只有非静态的成员方法，编译看左边，运行看右边

4.（b & 1) == 1 即=可以用来判断b是否为偶数 = 1则为奇数， = 0 则为偶数


5.线程轮流使用CPU：并发concurrent：同一时间应对多件事情的能力
一个CPU只能进行一个线程，在前一个任务未处理完无法处理下一个任务：串行
多个CPU独立的进行不同的线程：并行（parallel）：同一时间做多件事情的能力
一般并发并行同时进行。

6.Hashmap的遍历输出不会按照输入的顺序进行输出


7.异常的catch和throw，finally


8.char和int之间的转换
	8.1.char型可以通过 char c1 = 97; 语句进行赋值，c1得到对应的ASCII码的字符
	反之int类型也可以， int num1 = 'a';  num1得到对应的ASCII码
	8.2.char 和int 的运算
	char c2 = 'a' + 1;
	int num2 = 'a' + 1;
	System.out.println("c2:" + c2);
	System.out.println("num2:" + num2);
	System.out.println("(char) num2: " + (char) num2);
int转char类型需要强转符号

9.常见编译错误：
	9.1.中英文符号混用，少”；“

10.new ArrayList<Integer>(list)用来初始化一个内容和list相同的ArrayList

11.Collections.swap(List<?> list, int i, int j): 交换指定列表中指定位置的元素。  

12.comparator重写compare方法时，返回大于0，说明前面大于后面，如果不乱序默认按照升序

13.虽然List<Integer>不太好转换为int数组，但是ArrayList<int[]>可以通过调用toArray进行转换为int[][]
如：
List<int[]> list = new ArrayList<>();
int[][] res = list.toArray( new int[ list.size() ] );
返回类型<T> T[]   toArray(T[] a) 
以正确的顺序返回一个包含此列表中所有元素的数组（从第一个到最后一个元素）; 返回的数组的运行时类型是指定数组的运行时类型，如果列表适合指定的数组，则返回其中。 否则，将为指定数组的运行时类型和此列表的大小分配一个新数组。 如果列表适用于指定的数组，其余空间（即数组的列表数量多于此元素），则紧跟在集合结束后的数组中的元素设置为null 。 （这仅在调用者知道列表不包含任何空元素的情况下才能确定列表的长度。） 
因此，参数中的数组的长度必须小于等于list的总数量，否则，后面空出的位置会自动用null填满；

14.list.clear()用来快速清除List的所有元素。

15.几个概念的问题：
归并思想，分治思想，贪心算法快排思想

16.拷贝数组方式：
System.arraycopy(res, 0, nums, l, r + 1 - l);
参数：（源数组名，源数组开始复制的下标，目的数组名，目标数组开始复制的下标，复制的长度）
17.带权路径长度WPL(Weighted Path Length)
18.满二叉树：所有叶节点在同一层。且非叶节点的节点均有左右子树
完全二叉树：最下层的叶节点集中在左部连续位置
平衡二叉树：所有叶节点的高度相差不超过1，是二叉查找树/排序树的一种
二叉查找树/二叉排序树：左子树的所有值小于根节点的值，右子树的所有值都大于根节点的值


19.信号量 
P操作（wait操作）：资源数量--，（相当于验证阻塞队列中的数量）等待有限资源释放，将自身阻塞，挂起到阻塞队列
V操作（signal操作）：资源数量++，将有限资源释放，唤醒阻塞队列
信号量解决同步问题：
P操作放在后操作代码之前，V操作放在前操作代码之后，
锁（临界区/有限资源）为两个进程之间的权
实现互斥实在同一进程中实现一对PV操作
实现两进程的同步关系，是在其中一个进行中执行P，另一个进程中执行V
当出现套娃时，实现互斥的P操作一定要在同步的P操作之后，否则会发生死锁，即：同步在外，互斥在内，若互斥在外，另一个进程可能会无法进入同步块进行同步，但是V操作不会导致进程阻塞，两个V操作可以互换；

20.页：将进程划分的块，对应的大小就叫页面大小。

页框：将内存划分的块。

页和页框二者一一对应，一个页放入一个页框，（理论上）页的大小和页框的大小相等。

页表：就是一个页和页框一一对应的关系表。

页表项：记录一个页号和页内偏移量，占大小4B，32位

页号：记录该进程对应的内存分页号 

页号地址：页号所占位数，页面数目P=2^32B/2^12B=2^20页，同时得出页号地址是20位。

页内偏移量/块号：记录该进程在对应页号的偏移量

页内地址：页内偏移量所占位数，（偏移最小单位4B/32位）其最大值即可描述页面大小4KB= 2^12 B，即需要12位来描述偏移量

页面大小：一个页表项对应的块/页框的大小，占大小4K


21.Arrays.fill(数组名，值)；将一个数组全部充满某个数值

22.unicode是一个字符集，里面几乎包含了目前世界上已知的所有字符，且该字符集将二进制代码和字符形成一一映射，即一个字符对应且只对应一个二进制代码，反过来，一个二进制代码对应且只对应一个字符。如果我们有一个字符，那么通过Unicode字符集很容易找到该字符对应的二进制码，但是，反过来，可能会出现混乱，为什么呢？情境如下：如果我们假设Unicode字符集有两个字符，比如说（这里我没有查阅Unicode字符集，虚构了两个，只是打个比方，请各位看官不要较劲）：1100 1111 1111 0001 1111 0101 对应字符‘我’，一共三个字节，1100 1111 1111 0001对应字符‘你’，一共两个字节，恰好是‘我’这个字符二进制码的前两个字节。如果我们存的是‘我’即三个字节的那个代码，即1100 1111 1111 0001 1111 0101，然后让计算机进行解码时，它会从左向右依次读取一个数码，当读到1100 1111 1111 0001时它可能就停止，让‘你’这个字符与其对应，并不是我们当初想存的‘我’这个字符，只是因为这两个字符对应的二进制码前两个字节是一样的，这就是一个问题。Unicode字符集中对字符的编码是长度不确定的，其中有的字符是两个字符，有的是三个字符，这给计算机进行解码带来了困难（就像上述描述的情境），所以我们想给每个字符对应的二进制码前加上一个标记，让计算机看到这个标记就知道它将要读取几个字节，这样就防止了上述描述的两个字符因为前两个字节编码相同而误将三个字节的字符当成两个字节的字符读取了这种错误，那么这个标记该怎么加呢？这就导致人们引入UTF-8，它的英文全称是8-bit Unicode Transformation Format，可以看出它是将原本的Unicode码进行了transformation，这种transformation 就是给每个Unicode码进行标记，使得让计算机看到某个标记就知道待会要读取几个字节的代码，从而避免问题发生。

23.Maven只是引用，而并不是目录

24.？与T的区别，T一旦确定无法改变，然而？可以随意改变
如;　GenericFoo<Integer> foo1 = null;
     GenericFoo<Boolean> foo2 = null;
     //此时foo1只能接受GenericFoo<Integer>类型的实例，foo2只能接受GenericFoo<Boolean>类型的实例
可以这样声明:
    GenericFoo<? extends List> foo = null;
    foo = new GenericFoo<ArrayList>();
    foo = new GenericFoo<LinkedList>();

25.Java中的三个点"..."也就是动态参数,可变长，在形式参数里面出现，表示可变参数，即传入的参数可以随意,不论传多少个参数都被放到一个数组里面。

26.类.class =  对象.getClass() = Class.forName(String 全限定类名)

27.动态代理--原理是反射

28：编码
	ASCII编码：1个字节（8bits）表现出的256个状态--->最初西方用的编码，包含了所有英文字母和数字以及英文字符共128个字符

	ANSI码：
	ANSI编码是一种对ASCII码的拓展：ANSI编码用0x00–0x7f （即十进制下的0到127）范围的1 个字节来表示 1 个英文字符，超出一个字节的 0x80~0xFFFF 范围来表示其他语言的其他字符。
	也就是说，ANSI码仅在前128（0-127）个与ASCII码相同，之后的字符全是某个国家语言的所有字符。
	值得注意的是，两个字节最多可以存储的字符数目是2的16次方，即65536个字符，这对于一个语言的字符来说，绝对够了。
	还有ANSI编码其实包括很多编码：中国制定了GB2312编码，用来把中文编进去另外，日本把日文编到Shift_JIS里，韩国把韩文编到Euc-kr里，各国有各国的标准。受制于当时的条件，不同语言之间的ANSI码之间不能互相转换，这就会导致在多语言混合的文本中会有乱码。
	
	unicode编码：兼容了ASCII码，包含了所有文字和字符的字符集，所有文字都可以用两个字节表示：但存在的问题是
		1.计算机无法识别是几个字节的字符，若统一字节数英文字母前两个权威0.浪费存储空间；
		2.互联网的出现解决了unicode的使用问题
	
	UTF-8：实现unicode的方式之一，变长的编码方式，是unicode的一种编码规则，编码规则如下：
		1）对于单字节的符号，字节的第一位设为0，后面7位为这个符号的unicode码。因此对于英语字母，UTF-8编码和ASCII码是相同的。
		2）对于n字节的符号（n>1），第一个字节的前n位都设为1，第n+1位设为0，后面字节的前两位一律设为10。剩下的没有提及的二进制位，全部为这个符号的unicode码。
	举例：严”的unicode是4E25（100111000100101）最后一个二进制位开始，依次从后向前填入格式中的x，多出的位补0，“严”的UTF-8编码是“11100100 10111000 10100101”

29.解码
	1.JSP文件
	 浏览器的解码格式是在jsp中指定的，比如在jsp文件中经常可以看到这样两行代码。
	<!-- 
	　　这一句是和Tomcat说的：保存在硬盘上的jsp文件在被Tomcat翻译成servlet的时候，使用utf-8解码jsp文件的内容。
	　　如果不指定，会默认使用iso-8859-1来解码。
	-->
	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 

	<!-- 这一行是和浏览器说的：浏览器解码的时候请使用utf-8解码 -->
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	2.response可以设置内容的编码格式和指定浏览器的解码格式
	// 表示response的内容会以utf-8的编码方式编码后发送给浏览器。
	　　　　 response.setCharacterEncoding("UTF-8");
	
	// 告诉浏览器,解码的时候也要使用utf-8解码。
			 response.setContentType("text/html;charset=UTF-8");
	3.request对象可以指定应用服务用哪种编码格式来解码接收到的数据.
	// 通过这句话，可以指定用utf-8编码格式来解码浏览器传来的数据。不过只对post方式传来的数据有效。如果是get方法传来的数据，还是会以默认的iso-8859-1来解码。
	　　　　request.setCharacterEncoding("UTF-8");
	4.设置tomcat服务器配置文件server.xml，指定以何种编码解码浏览器传来的参数。
		<Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8" />

30.一个DAO对应着一个sql语句，一个事务业务对应多条DAO即多个sql语句

31.Comparator和Comparable接口的区别
Comparator 类似于一个现有的比较器，是一种函数型接口，现创建现用的工具，如sort方法可以放入一个新重写的比较器。
Comparable是一个类可以继承的接口，代表这个类可以用来比较，比如包装类Integer，Double等都继承热这个接口，意味着直接调用sort方法是，就会调用内部的Comparable重写的compareTo方法

32.跳出循环渐进的语句容易忘写，如cur = cur.next等，全局变量和局部变量一般没有考虑周全，比如循环过后置0等，重置min=Integer.MAX_VALUE

33.将一个元素放入优先队列的时间复杂度为 O(logn)

34.String[] dataString = data.split(","); String可以通过split方法进行分解为一个个String数组

35.DFS中慎用++,因为会是本身自增或自减

36.在求位数过程中，一种优美的写法是在while或者for循环中，先定义digit =1 然后

int digit = 1;
while(){
	digit *= 10;
}

37.SQL中的join
A
+------+---------+----------------+
| id_P | name    | city           |
+------+---------+----------------+
|    1 | Person1 | Oxford Street  |
|    2 | person2 | Fifth Avenue   |
|    3 | person3 | Changan Street |
+------+---------+----------------+

B
+------+------------+------+
| id_o | order_type | id_p |
+------+------------+------+
|    1 | food       | 3    |
|    2 | book       | 3    |
|    3 | PC         | 1    |
|    4 | water      | 1    |
|    5 | card       | 25   |
+------+------------+------+
1.
table A 和 table B的重合以及非重合部分
A ∩ （A ∪ B）= A，A中所有元素都有，并且可能有多条
SELECT a.id_P , a.name, b.order_type FROM persons a LEFT JOIN orders b ON a.id_P = b.id_P ORDER BY id_P;
SELECT <select_list> FROM TABLEA A LEFT JOIN TABLEB B ON A.key = B.key 
+------+---------+------------+
| id_P | name    | order_type |
+------+---------+------------+
|    1 | Person1 | PC         |
|    1 | Person1 | water      |
|    2 | person2 | NULL       |
|    3 | person3 | food       |
|    3 | person3 | book       |
+------+---------+------------+

2.
B ∩ （A ∪ B）= B，B中所有元素都有，并且可能有多条
SELECT b.id_o , a.name, b.order_type FROM persons a right JOIN orders b ON a.id_P = b.id_P ORDER BY id_o;
SELECT <select_list> FROM TABLEA A RIGHT JOIN TABLEB B ON A.key = B.key 
+------+--------+------------+
| id_o | name   | order_type |
+------+--------+------------+
|    1 | Carter | food      |
|    2 | Carter | book       |
|    3 | Adams  | PC         |
|    4 | Adams  | water      |
|    5 | NULL   | card       |
+------+--------+------------+

3.A 和 B 中 A独有的
SELECT a.id_p, a.name, b.order_type FROM persons a LEFT JOIN orders b ON a.id_p = b.id_p WHERE b.id_p IS NULL;
SELECT <select_list> FROM TABLEA A RIGHT JOIN TABLEB B ON A.key = B.key WHERE B.key is NULL;
+------+---------+------------+
| id_p | name    | order_type |
+------+---------+------------+
|    2 | person2 | NULL       |
+------+---------+------------+
4.A和B中B独有的
SELECT b.id_o ,b.order_type FROM persons a RIGHT JOIN orders b ON a.id_p = b.id_p WHERE a.id_p IS NULL;
SELECT <select_list> FROM TABLEA A RIGHT JOIN TABLEB B ON A.key = B.key WHERE A.key is NULL;
+------+------------+
| id_o | order_type |
+------+------------+
|    5 | card       |
+------+------------+

5.A ∩ B
SELECT a.id_P , a.name, a.city, b.order_type FROM persons a INNER JOIN orders b ON a.id_p = b.id_p ORDER BY a.id_p; 
SELECT <select_list> FROM TABLEA A INNER JOIN TABLEB B ON A.key = B.key;
+------+---------+----------------+------------+
| id_P | name    | city           | order_type |
+------+---------+----------------+------------+
|    1 | Person1 | Oxford Street  | water      |
|    1 | Person1 | Oxford Street  | PC         |
|    3 | person3 | Changan Street | book       |
|    3 | person3 | Changan Street | food       |
+------+---------+----------------+------------+

6.A ∪ B
mysql不支持full join 和 full outer join，会报错：
You have an error in your SQL syntax; 
check the manual that corresponds to your MySQL server version for the right syntax to use near 'full join 
orders b on a.id_p = b.id_p order by a.id_p LIMIT 0, 1000' at line 1
我们采取union的方式进行联合处理
SELECT * FROM persons a LEFT JOIN orders b ON a.id_P = b.id_P
UNION
SELECT * FROM persons a RIGHT JOIN orders b ON a.id_p = b.id_p;
+------+---------+----------------+------+------------+------+
| id_P | name    | city           | id_o | order_type | id_p |
+------+---------+----------------+------+------------+------+
|    3 | person3 | Changan Street |    1 | food       | 3    |
|    3 | person3 | Changan Street |    2 | book       | 3    |
|    1 | Person1 | Oxford Street  |    3 | PC         | 1    |
|    1 | Person1 | Oxford Street  |    4 | water      | 1    |
|    2 | person2 | Fifth Avenue   | NULL | NULL       | NULL |
| NULL | NULL    | NULL           |    5 | card       | 25   |
+------+---------+----------------+------+------------+------+

7.A独有和B独有的
 SELECT * FROM persons a LEFT JOIN orders b ON a.id_p = b.id_p WHERE b.id_p IS NULL
UNION
SELECT * FROM persons a RIGHT JOIN orders b ON a.id_p = b.id_p WHERE a.id_p IS NULL;
+------+---------+--------------+------+------------+------+
| id_P | name    | city         | id_o | order_type | id_p |
+------+---------+--------------+------+------------+------+
|    2 | person2 | Fifth Avenue | NULL | NULL       | NULL |
| NULL | NULL    | NULL         |    5 | card       | 25   |
+------+---------+--------------+------+------------+------+
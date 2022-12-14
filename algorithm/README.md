### 记录一下在学习王争的《数据结构与算法之美》专栏过程中的算法代码实现

---
### Java中常见数据结构 【备忘】
#### 一、List
1. **ArrayList----数组**<br/>
   允许对元素进行快速随机访问,适合随机查找和遍历，不适合插入和删除
2. **Vector----数组实现，线程同步**<br/>
   某一时刻只有一个线程能够写 Vector，避免多线程同时写而引起的不一致性，但实现同步需要很高的花费，因此访问它比访问 ArrayList 慢
3. **LinkedList----双向链表(JDK1.6之前为循环链表，JDK1.7取消了循环)**<br/>
   实现了List接口和Deque接口的双端链表,具有队列的特性,非线程安全<br/>适合数据的动态插入和删除,随机访问和遍历速度比较慢,另外，还提供了 List 接口中没有定义的方法，专门用于操作表头和表尾元素，可以当作堆栈、队列和双向队列使用
#### 二、Set
> **_Set 注重独一无二的性质_**<br/>
对象的相等性本质是对象 hashCode 值（java 是依据对象的内存地址计算出的此序号）判断的，如果想要让两个不同的对象视为相等的，就必须覆盖 Object 的 hashCode 方法和 equals 方法
1. **HashSet----基于 HashMap 实现** <br/>
   无序唯一
2. **LinkedHashSet---- HashSet + LinkedHashMap**<br/>
   是HashSet的子类，底层使用了LinkedHashMap，在HashSet的哈希表数据结构基础之上，增加了一个 **_双向链表_** 用来记录元素添加的顺序，能按照添加顺序遍历输出。需要频繁遍历的话效率可能高于HashSet，非线程安全
#### 三、Map
1. **HashMap----数组+链表+红黑树**<br/>
   JDK1.8之前HashMap由数组+链表组成的，数组是HashMap的主体，链表则是主要为了解决哈希冲突而存在的（*拉链法* 解决冲突）。JDK1.8以后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为8）时，将链表转化为红黑树，以减少搜索时间。<br/>
   >HashMap 根据键的 hashCode 值存储数据，大多数情况下可以直接定位到它的值，因而具有很快的访问速度，但遍历顺序却是不确定的。 HashMap 最多只允许一条记录的键为 null，允许多条记录的值为 null。HashMap 非线程安全，即任一时刻可以有多个线程同时写 HashMap，可能会导致数据的不一致。如果需要满足线程安全，可以用 Collections 的 synchronizedMap 方法使 HashMap 具有线程安全的能力，或者使用 ConcurrentHashMap。
    
   >在 Java8 中，当链表中的元素超过了 8 个以后，会将链表转换为红黑树，在这些位置进行查找的时候可以降低时间复杂度为 O(logN)
2. **ConcurrentHashMap----支持并发操作，线程安全**<br/>
   1. Segment段<br/>
      整个 ConcurrentHashMap 由一个个 Segment 组成，Segment 代表”部分“或”一段“的意思，所以很多地方都会将其描述为分段锁
   2. 线程安全（Segment 继承 ReentrantLock 加锁）<br/>
      ConcurrentHashMap 是一个 Segment 数组，Segment 通过继承ReentrantLock 来进行加锁，所以每次需要加锁的操作锁住的是一个 segment，这样只要保证每个 Segment 是线程安全的，也就实现了全局的线程安全
   3. 并行度（默认 16）<br/>
      concurrencyLevel：并行级别、并发数、Segment 数，怎么翻译不重要，理解它。默认是 16，也就是说 ConcurrentHashMap 有 16 个 Segments，所以理论上，这个时候，最多可以同时支持 16 个线程并发写，只要它们的操作分别分布在不同的 Segment 上。这个值可以在初始化的时候设置为其他值，但是一旦初始化以后，它是不可以扩容的。
3. **TreeMap----红黑树（平衡二叉排序树）**<br/>
   TreeMap 实现 SortedMap 接口，能够把它保存的记录根据键排序，默认是按键值的升序排序，也可以指定排序的比较器，当用 Iterator 遍历 TreeMap 时，得到的记录是排过序的。<br/>
   在使用 TreeMap 时，key 必须实现 Comparable 接口或者在构造 TreeMap 传入自定义的 Comparator，否则会在运行时抛出 java.lang.ClassCastException 类型的异常
4. **HashTable----数组+链表**<br/>
   Hashtable 是遗留类，很多映射的常用功能与 HashMap 类似，不同的是它承自 Dictionary 类，并且是 **_线程安全_** 的，任一时间只有一个线程能写 Hashtable，并发性不如 ConcurrentHashMap，因为 ConcurrentHashMap 引入了分段锁。<br/>
   Hashtable 不建议在新代码中使用，不需要线程安全的场合可以用 HashMap 替换，需要线程安全的场合可以用 ConcurrentHashMap 替换
5. **LinkedHashMap----记录插入顺序**<br/>
   LinkedHashMap 是 HashMap 的一个子类，在其基础上，增加了一条双向链表，使得可以保持键值对的插入顺序，在用 Iterator 遍历 LinkedHashMap 时，先得到的记录肯定是先插入的，也可以在构造时带参数，按照访问次序排序

#### 四、栈
##### 1.0版本栈结构Stack类

Stack 继承了 Vector 类，从 Vector 中继承了几个字段
* elementData :存储向量组件的数组缓冲区 – 我将其看作对象的引用
* elementCount : 对象中的有效组件数
* capacityIncrement : 向量的大小大于其容量时，容量自动增加的量。（与 Vector 一样，Stack 除了初始大小之外，可以自动进行扩容，扩容方式与 Vector 相同：正常情况下为原来大小的2倍)

##### 1.6版本栈结构Deque接口

Deque 是一个线性的collection，支持在两端插入和移除元素。deque 是 “double ended queue（双端队列）” 的缩写。
大多数 Deque 实现对于它们能够包含的元素数没有固定限制，但此接口既支持有容量限制的双端队列，也支持没有固定大小限制的双端队列。

Deque 具有双端队列特性，有以下两种实现：
* 作为 FIFO 的队列 —— LinkedList 类
* 作为 FILO 的栈 —— ArrayDeque 类

##### ArrayDeque栈类

#### 查看对应章节
 - [链表篇](https://github.com/Echoidf/computer-course/tree/main/algorithm/src/main/java/%E9%93%BE%E8%A1%A8%E7%AF%87)
 - [队列篇](https://github.com/Echoidf/computer-course/tree/main/algorithm/src/main/java/%E9%98%9F%E5%88%97%E7%AF%87)
 - [堆栈篇](https://github.com/Echoidf/computer-course/tree/main/algorithm/src/main/java/%E5%A0%86%E6%A0%88%E7%AF%87)
 - [递归篇](https://github.com/Echoidf/computer-course/tree/main/algorithm/src/main/java/%E9%80%92%E5%BD%92%E7%AF%87)
 - [排序篇](https://github.com/Echoidf/computer-course/tree/main/algorithm/src/main/java/%E6%8E%92%E5%BA%8F%E7%AF%87)




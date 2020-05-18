package tree.huffmancode;/*
    @author shl
    @create 2020-04-15-16:25
*/
//哈夫曼编码
import java.io.*;
import java.util.*;

public class HuffmanCode {

    public static void main(String[] args) {
        //要被编码的内容
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();//转换为对应的ASCII值
        System.out.println("压缩前的字符长度:"+contentBytes.length);


        //封装之后：
        byte[] huffmanZipCodesBytes = huffmanZip(contentBytes);

        System.out.println("压缩后的结果：" + Arrays.toString(huffmanZipCodesBytes) + ",长度为"  + huffmanZipCodesBytes.length);

        //解码测试
        byte[] sourceBytes = decode(huffmanCode, huffmanZipCodesBytes);
        System.out.println("解码后字符串："+new String(sourceBytes));
        System.out.println("解码后字符串长度："+new String(sourceBytes).length());

        List<Node> nodes = getNodes(contentBytes);
        System.out.println("字符中各元素出现的次数为=》");
        System.out.println("nodes = "+ nodes);

        System.out.println("huffman树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        huffmanTreeRoot.preOrder();

        //测试生成的Huffman编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println("生成的Huffman编码表");
        System.out.println(huffmanCodes);

        //
        byte[] huffmanCodesBytes = zip(contentBytes, huffmanCodes);
        System.out.println( "huffman编码后的对应的字节数组：" + Arrays.toString(huffmanCodesBytes));
        System.out.println("压缩后的长度：" + huffmanCodesBytes.length);

    }

    //对文件的压缩：

    /**
     *
     * @param srcFile 希望压缩的文件的路径
     * @param dstFile 压缩后的文件所在的目录
     */
    public static void zipFile(String srcFile,String dstFile)  {
        //创建一个文件输入流
        FileInputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte b[] = new byte[is.available()];
            //读取文件
            is.read(b);
            //获取Huffman编码表,对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件得输出流
            os = new FileOutputStream(dstFile);
            //创建和文件输出相关联的objectOutputStream对象输出流
            oos = new ObjectOutputStream(os);
            //以对象流的方式写入Huffman编码和字节数组
            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCode);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                    is.close();
                    oos.close();
                    os.close();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    //方法：对压缩文件解压

    /**
     *
     * @param zipFile 准备解压的文件路径
     * @param dstFile 解压到的路径
     */
    public static void unZipFile(String zipFile,String dstFile) {

        //文件输入流
        InputStream is = null;
        //对象输入流
        ObjectInputStream ois = null;
        //文件输出流
        OutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            //创建一个和is关联的输入流
            ois = new ObjectInputStream(is);
            //读取byte数组
            byte[] huffmanBytes =(byte[])ois.readObject();
            //读取Huffman编码表
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes写入目标文件
            os = new FileOutputStream(dstFile);
            //写数据到文件中
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    //数据的解压
    //1.将HuffmanCodeBytes重新要转成Huffman对应的二进制字符串
    //2.Huffman编码对应的字符串转化成对应Huffman编码前的字符串

    //方法1
    /**
     * 将一个byte转成一个二进制的字符串
     * @param b
     * @param flag true表示需要补高位，false表示不补高位
     * @return 返回b对应的二进制字符串（按补码返回）
     */
    private static String byteToBitString(boolean flag,byte b) {
        //使用变量保存b
        int temp = b;
        //如果是正数，还要补高位
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    //对压缩数据的解码

    /**
     *
     * @param huffmanCodes 哈夫曼编码map
     * @param huffmanBytes 哈夫曼编码得到的字节数组
     * @return 返回原来字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes) {

        //1、得到HuffmanBytes对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //2、将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte bytes = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag,bytes));

        }
        System.out.println("huffman数组对应的二进制字符串 ="+ stringBuilder.toString());

        //把字符串按照指定哈夫曼进行解码
        //把哈夫曼编码表进行调换，进行反向查询
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte,String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(),entry.getKey());
        }
        System.out.println("map =" + map );

        //创建一个集合，存放bytes
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            //i就是索引，扫描stringbuilder
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //取出一个字符
                String key = stringBuilder.substring(i,i+count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        //for循环结束后list中就存放了所有的字符
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length ; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    //封装方法，便于调用

    /**
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 经过Huffman编码处理后的字节数组（压缩后的数组）
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根据nodes创建的哈夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //对应的哈夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据Huffman编码对原始数据进行压缩
        byte[] huffmanCodesBytes = zip(bytes,huffmanCodes);
        return huffmanCodesBytes;
    }

    //将字符串对应的byte数组，通过生成的Huffman编码表，返回一个压缩后的byte数组

    /**
     *
     * @param bytes 原始字符串对应的byte数组
     * @param huffmanCode 生成的Huffman编码
     * @return 返回字符串处理过后的byte数组
     */
    private static byte[] zip(byte[] bytes, Map<Byte,String> huffmanCode) {
        //1、利用HuffmanCodes编码表将bytes转成Huffman对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //2、遍历bytes数组
        for (byte b:
            bytes ) {
                stringBuilder.append(huffmanCode.get(b));
        }
        //将对应字符串转为byte数组
        //统计返回的 HuffmanCodes长度
        int len;
        //或者一句话：len = (stringBuilder.length() + 7) / 8;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] HuffmanCodesBytes = new byte[len];
        int index = 0; //计数器，记录第几个byte
        for (int i = 0; i < stringBuilder.length() ; i += 8) {
            //步长为8，因为每8位对应一个byte
            String strBytes;
            if ( i + 8 >stringBuilder.length()) {
                //说明不够8位
                strBytes = stringBuilder.substring(i);
            }else {

                strBytes = stringBuilder.substring(i,i + 8);

            }
            //将strByte转成一个byte数组，放入到HuffmanCodesBytes中
            HuffmanCodesBytes[index] = (byte) Integer.parseInt(strBytes,2);
            index ++;
        }
        return HuffmanCodesBytes;
    }


    //生成哈夫曼树对应的哈夫曼编码
    //1.将哈夫曼编码表存放在Map<Bytes,String> 字符ASCII码值->对应的哈夫曼编码
    static Map<Byte, String> huffmanCode = new HashMap<>();
    //2.在生成哈夫曼编码表时，需要拼接路径。stringBuilder存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 将传入的node结点的所有叶子节点的Huffman编码得到，并放入HuffmanCode集合中
     *  仔细消化！
     * @param node 传入节点
     * @param code 路径：左0右1
     * @param stringBuilder 用于拼接上面的路径
     */
    private static void getCodes(Node node,String code,StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        //将传入的code加入到stringbuilder1中
        stringBuilder1.append(code);
        if (node != null) {
            //node == null不做处理了
            //不为null，就判断当前node是叶子节点还是非叶子节点
            if (node.data == null) {
                //说明是非叶子节点
                //递归处理
                getCodes(node.left,"0",stringBuilder1);//左递归
                getCodes(node.right,"1",stringBuilder1);//右递归
            } else {
                //说明是一个叶子节点
                //表示找到某个叶子节点的最后了
                huffmanCode.put(node.data,stringBuilder1.toString());
            }
        }


    }
    //对上述getCodes方法进行重载，调用方便
    private static Map<Byte,String> getCodes (Node root) {
        if (root == null) {
            System.out.println("空树");
            return null;
        }
        //处理左子树
        getCodes(root.left,"0",stringBuilder);
        //处理右子树
        getCodes(root.right,"1",stringBuilder);
        return huffmanCode;
    }


    //方法：获取编码内容的每个字符（数据）和其对应的个数，存放在list中
    /**
     *
     * @param bytes 传入转换后的字节数组
     * @return 返回编码内容和对应个数构成的集合list
     */
    private static List<Node> getNodes(byte[] bytes) {

        //1.创建一个list
        ArrayList<Node> nodes = new ArrayList<>();
        //2.存储每个byte出现的次数：遍历bytes，统计每个byte出现的次数->map[key,value]
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b:
            bytes ) {
            Integer count = counts.get(b);
            if (count == null) {
                //map还没有字符数据，是第一次
                counts.put(b,1);
            } else {
                counts.put(b,count + 1);
            }
        }
        //把每个键值对转为Node对象，并加入nodes集合中
        //遍历map
        //???? 回去复习集合！
        for (Map.Entry<Byte,Integer> entry : counts.entrySet() ) {
            nodes.add(new Node(entry.getKey() , entry.getValue()));
        }
        return nodes;
    }

    //通过list构建一个哈夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序
            Collections.sort(nodes);//从小到大排序

        //取出最小的值得两个节点
        Node leftNode = nodes.get(0);
        Node rightNode = nodes.get(1);
        //创建新的二叉树，根节点没有data属性，只有权值
        Node parent = new Node(null,leftNode.weight + rightNode.weight);
        parent.left = leftNode;
        parent.right = rightNode;

        //处理已经用过的节点，删除
        nodes.remove(leftNode);
        nodes.remove(rightNode);
        //新的二叉树节点加入
        nodes.add(parent);
        }
        //最后的节点就是哈夫曼树的根节点
        return nodes.get(0);
    }

    //前序遍历
    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("huffman树为空");
        }
    }
}

//创建新的newNode节点，含数据与权值

class Node implements Comparable<Node> {

    Byte data;//存放数据/字符本身
    int weight;//权值，即数据/字符出现的次数
    //构建哈夫曼树的左子节点和右子节点
    Node left;
    Node right;

    public Node() {
    }

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
    //前序遍历
    public void preOrder() {

        System.out.println(this);

        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}


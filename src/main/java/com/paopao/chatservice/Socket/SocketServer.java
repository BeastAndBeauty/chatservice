package com.paopao.chatservice.Socket;

import net.sf.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：paopao
 * @date ：Created in 2019/4/4 14:44
 * @description：Socket服务端
 */

public class SocketServer {

    private static final int PORT = 8888;
    private List<Socket> socketList = new ArrayList<>();
    private List<String> nicknames = new ArrayList<>();
    private ServerSocket serverSocket = null;
    //线程池对象
    private ExecutorService executorService = null;
    private Socket socket;
    private BufferedReader bufferedReader = null;
    private String message = "";

    public static void main(String[] args) {
        new SocketServer();
    }
    //启动服务器，等待客服端连接
    public SocketServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            //创建线程池
            executorService = Executors.newCachedThreadPool();
            System.out.println("服务器已启动，等待客服端连接");
//            final Socket socket = null;
            //用死循环等待多个客服端的连接，连接一个就启动一个线程进行管理
            while (true) {
                socket = serverSocket.accept();
                //把客服端放入集合中
                socketList.add(socket);
                //启动一个线程，用已守候从客服端发来的消息
//                executorService.execute(new Service(socket));
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            message = "";
                            //客户端只要一连到服务器，便发送连接成功的信息
//                            System.out.println("**********************************");
//                            System.out.println("服务器地址：" + socket.getInetAddress());
//                            System.out.println("当前连接总数：" + socketList.size());
//                            System.out.println("**********************************");


                            while (true) {
                                if ((message = bufferedReader.readLine()) != null) {
                                    //当客户端发送的信息为：exit时，关闭连接

                                    //接收客服端发过来的信息message，然后转发给客服端
                                    sendMessage(message);

                                }
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void sendMessage(String msg) throws IOException {
        JSONObject jsonObject = JSONObject.fromObject(msg);
        //记录改用户时候已在列表里
        boolean isExist = false;

        for (int i = 0; i < nicknames.size(); i++) {
            if (nicknames.get(i).equals(jsonObject.get("Account"))) {
                isExist = true;
                break;
            }
        }

        //显示用户列表
        if (!isExist) {
            nicknames.add(jsonObject.get("Account") + "");
            System.out.println("**********************************");
            System.out.println("用户列表:");
            for (String s : nicknames)
                System.out.print(s + "   ");
            System.out.println();
            System.out.println("当前连接总数：" + nicknames.size());
            System.out.println("**********************************");
        }

//        //message=Exit,退出聊天
//        if (jsonObject.get("Message").equals("Exit")) {
//            closeSocket();
//            return;
//        }
        if (jsonObject.get("MessageType").equals("Image"))
            System.out.println(jsonObject.get("Account") + "---图片二进制流-->" + (jsonObject.get("Message")+"").substring(0,10)+"...");
        else
            System.out.println(jsonObject.get("Account") + "---文本内容-->" + jsonObject.get("Message"));

        for (int i = 0; i < socketList.size(); i++) {
            Socket socket = socketList.get(i);
            PrintWriter printWriter = null;
            try {
                //创建输出流对象
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                //转发
                printWriter.println(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭客服端
     *
     * @throws IOException
     */
    private void closeSocket() throws IOException {
        socketList.remove(socket);
        bufferedReader.close();
//        message = "主机：" + socket.getInetAddress() + "关闭连接\n目前在线:"
//                + socketList.size();
        socket.close();
//        this.sendMessage(message);
    }

}

package com.dcw.adaoframework.dao;

import java.io.Serializable;

/**
 * <p>Title: ucweb</p>
 * <p/>
 * <p>Description: </p>
 * ......
 * <p>Copyright: Copyright (c) 2015</p>
 * <p/>
 * <p>Company: ucweb.com</p>
 *
 * @author JiaYing.Cheng
 * @version 1.0
 * @email adao12.vip@gmail.com
 * @create 15/3/12
 */
public interface ITreeModel<PK extends Serializable> {

    PK getNodeId();

    PK getNodePid();

    String getNodeName();

    int getNodeOrder();

    String getNodeImage();

    int getNodeLevel();

    void setNodeId(PK nodeId);

    void setNodePid(PK nodePid);

    void setNodeName(String nodeName);

    void setNodeOrder(PK nodeOrder);

    void setNodeImage(String nodeImage);

    void setNodeLevel(int nodelLevel);
}


package com.schneewittchen.rosandroid.widgets.gridmap;

import com.schneewittchen.rosandroid.widgets.base.BaseData;
import com.schneewittchen.rosandroid.widgets.base.BaseEntity;
import com.schneewittchen.rosandroid.widgets.base.BaseNode;

import org.ros.message.MessageListener;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Subscriber;

import nav_msgs.MapMetaData;

/**
 * TODO: Description
 *
 * @author Nils Rottmann
 * @version 1.0.0
 * @created on 20.04.20
 * @updated on 07.05.20
 * @modified by Nico Studt
 */
public class GridMapNode extends BaseNode {


    @Override
    public void onStart(ConnectedNode connectedNode) {
        Subscriber<nav_msgs.OccupancyGrid> subscriber = connectedNode.newSubscriber(
                widget.subPubNoteEntity.topic,
                widget.subPubNoteEntity.messageType
        );

        subscriber.addMessageListener(occupancyGrid -> {
            MapMetaData info = occupancyGrid.getInfo();
            int width = info.getWidth();
            int height = info.getHeight();
            byte[] array = occupancyGrid.getData().array();
            float res = info.getResolution();
            float x0 = (float) info.getOrigin().getPosition().getX();
            float y0 = (float) info.getOrigin().getPosition().getY();

            GridMapData data = new GridMapData(width, height, array, res, x0, y0);
            data.setId(widget.id);
            listener.onNewData(data);
        });
    }

    @Override
    public void onNewData(BaseData data) {
    }
}
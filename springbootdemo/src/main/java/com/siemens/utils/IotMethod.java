package com.siemens.utils;

import com.amazonaws.services.iot.AWSIot;
import com.amazonaws.services.iot.AWSIotClientBuilder;
import com.amazonaws.services.iot.model.ListThingsRequest;
import com.amazonaws.services.iot.model.ListThingsResult;
import com.amazonaws.services.iotdata.AWSIotData;
import com.amazonaws.services.iotdata.AWSIotDataClientBuilder;
import com.amazonaws.services.iotdata.model.GetThingShadowRequest;
import com.amazonaws.services.iotdata.model.GetThingShadowResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IotMethod {
    private static final Logger logger = LoggerFactory.getLogger(IotMethod.class);

    private AWSIotData awsIotData;
    private AWSIot awsIot;

    public IotMethod(AWSIotData awsIotData, AWSIot awsIot) {
        this.awsIotData = awsIotData;
        this.awsIot = awsIot;
    }

    public IotMethod() {
        awsIotData = AWSIotDataClientBuilder.standard().build();
        awsIot = AWSIotClientBuilder.standard().build();
    }

    public String getThingShadow(String thingName) {
        try {
            GetThingShadowResult getThingShadowResult = awsIotData.getThingShadow(new GetThingShadowRequest()
                    .withThingName(thingName));
            return new String(getThingShadowResult.getPayload().array());
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            return null;
        }
    }

    public ListThingsResult listThings(String type, String nextToken) {
        if (nextToken == null) {
            return awsIot.listThings(new ListThingsRequest().withThingTypeName(type).withMaxResults(250));
        }
        return awsIot.listThings(new ListThingsRequest().withNextToken(nextToken)
                .withThingTypeName(type).withMaxResults(250));
    }
}

package com.cloud.apigateway.filter;

import com.cloud.apigateway.exception.RateLimitException;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.ZuulFilterResult;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

@Component
public class RateLimitFilter extends ZuulFilter {

    // 一秒100个令牌
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

    // 是否执行过滤
    @Override
    public boolean shouldFilter() {
        return true;
    }

    // 具体逻辑
    @Override
    public Object run() throws ZuulException {
        if (!RATE_LIMITER.tryAcquire()) {
            //  自定义的异常
            throw new RateLimitException("已达上限，开始限流");
        }
        return null;
    }

    public RateLimitFilter() {
        super();
    }

    // 过滤类型
    @Override
    public String filterType() {
        return PRE_TYPE;
    }


    // 常量复用于org.springframework.cloud.netflix.zuul.filters.support.FilterConstants
    // 执行顺序，在PreDecorationFilter之前执行
    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean isStaticFilter() {
        return super.isStaticFilter();
    }

    @Override
    public String disablePropertyName() {
        return super.disablePropertyName();
    }

    @Override
    public boolean isFilterDisabled() {
        return super.isFilterDisabled();
    }

    @Override
    public ZuulFilterResult runFilter() {
        return super.runFilter();
    }

    @Override
    public int compareTo(ZuulFilter filter) {
        return super.compareTo(filter);
    }
}

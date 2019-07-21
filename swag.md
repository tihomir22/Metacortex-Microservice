--- 

title: Api Documentation 

language_tabs: 
   - shell 

toc_footers: 
   - <a href='#'>Sign Up for a Developer Key</a> 
   - <a href='https://github.com/lavkumarv'>Documentation Powered by lav</a> 

includes: 
   - errors 

search: true 

--- 

# Introduction 

Api Documentation 

**Version:** 1.0 

# /
## ***GET*** 

**Summary:** getAll

### HTTP Request 
`***GET*** /` 

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

# /ERROR
## ***GET*** 

**Summary:** errorHtml

### HTTP Request 
`***GET*** /error` 

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

## ***POST*** 

**Summary:** errorHtml

### HTTP Request 
`***POST*** /error` 

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 201 | Created |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

## ***PUT*** 

**Summary:** errorHtml

### HTTP Request 
`***PUT*** /error` 

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 201 | Created |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

## ***DELETE*** 

**Summary:** errorHtml

### HTTP Request 
`***DELETE*** /error` 

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 204 | No Content |
| 401 | Unauthorized |
| 403 | Forbidden |

## ***OPTIONS*** 

**Summary:** errorHtml

### HTTP Request 
`***OPTIONS*** /error` 

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 204 | No Content |
| 401 | Unauthorized |
| 403 | Forbidden |

## ***PATCH*** 

**Summary:** errorHtml

### HTTP Request 
`***PATCH*** /error` 

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 204 | No Content |
| 401 | Unauthorized |
| 403 | Forbidden |

# /HISTORIC/**
## ***GET*** 

**Summary:** getHistoric

### HTTP Request 
`***GET*** /historic/**` 

**Parameters**

| Name | Located in | Description | Required | Type |
| ---- | ---------- | ----------- | -------- | ---- |
| base | query | Base asset | Yes | string |
| counter | query | Counter asset | Yes | string |
| historicInterval | query | Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M] | Yes | string |
| queryParameters | query | queryParameters | Yes |  |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

# /PRICE/**
## ***GET*** 

**Summary:** getPrice

### HTTP Request 
`***GET*** /price/**` 

**Parameters**

| Name | Located in | Description | Required | Type |
| ---- | ---------- | ----------- | -------- | ---- |
| base | query | Base asset | Yes | string |
| counter | query | Counter asset | Yes | string |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

# /TECHNICAL/DEMA/**
## ***GET*** 

**Summary:** recuperarDEMA

### HTTP Request 
`***GET*** /technical/dema/**` 

**Parameters**

| Name | Located in | Description | Required | Type |
| ---- | ---------- | ----------- | -------- | ---- |
| base | query | Base asset | Yes | string |
| counter | query | Counter asset | Yes | string |
| historicInterval | query | Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M] | Yes | string |
| indicatorInterval | query | Tecnichal's Indicator Interval [10,20,50,100,200,500,...] | Yes | integer |
| queryParameters | query | queryParameters | Yes |  |
| seriesType | query | Price's series type [open,close,high,low] | Yes | string |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

# /TECHNICAL/EMA/**
## ***GET*** 

**Summary:** recuperarEMA

### HTTP Request 
`***GET*** /technical/ema/**` 

**Parameters**

| Name | Located in | Description | Required | Type |
| ---- | ---------- | ----------- | -------- | ---- |
| base | query | Base asset | Yes | string |
| counter | query | Counter asset | Yes | string |
| historicInterval | query | Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M] | Yes | string |
| indicatorInterval | query | Tecnichal's Indicator Interval [10,20,50,100,200,500,...] | Yes | integer |
| queryParameters | query | queryParameters | Yes |  |
| seriesType | query | Price's series type [open,close,high,low] | Yes | string |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

# /TECHNICAL/KAMA/**
## ***GET*** 

**Summary:** recuperarKAMA

### HTTP Request 
`***GET*** /technical/kama/**` 

**Parameters**

| Name | Located in | Description | Required | Type |
| ---- | ---------- | ----------- | -------- | ---- |
| base | query | Base asset | Yes | string |
| counter | query | Counter asset | Yes | string |
| historicInterval | query | Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M] | Yes | string |
| indicatorInterval | query | Tecnichal's Indicator Interval [10,20,50,100,200,500,...] | Yes | integer |
| queryParameters | query | queryParameters | Yes |  |
| seriesType | query | Price's series type [open,close,high,low] | Yes | string |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

# /TECHNICAL/MAMA/**
## ***GET*** 

**Summary:** recuperarMAMA

### HTTP Request 
`***GET*** /technical/mama/**` 

**Parameters**

| Name | Located in | Description | Required | Type |
| ---- | ---------- | ----------- | -------- | ---- |
| base | query | Base asset | Yes | string |
| counter | query | Counter asset | Yes | string |
| historicInterval | query | Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M] | Yes | string |
| indicatorInterval | query | Tecnichal's Indicator Interval [10,20,50,100,200,500,...] | Yes | integer |
| queryParameters | query | queryParameters | Yes |  |
| seriesType | query | Price's series type [open,close,high,low] | Yes | string |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

# /TECHNICAL/RSI/**
## ***GET*** 

**Summary:** recuperarRSI

### HTTP Request 
`***GET*** /technical/rsi/**` 

**Parameters**

| Name | Located in | Description | Required | Type |
| ---- | ---------- | ----------- | -------- | ---- |
| base | query | Base asset | Yes | string |
| counter | query | Counter asset | Yes | string |
| historicInterval | query | Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M] | Yes | string |
| indicatorInterval | query | Tecnichal's Indicator Interval [10,20,50,100,200,500,...] | Yes | integer |
| queryParameters | query | queryParameters | Yes |  |
| seriesType | query | Price's series type [open,close,high,low] | Yes | string |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

# /TECHNICAL/SMA/**
## ***GET*** 

**Summary:** recuperarSMA

### HTTP Request 
`***GET*** /technical/sma/**` 

**Parameters**

| Name | Located in | Description | Required | Type |
| ---- | ---------- | ----------- | -------- | ---- |
| base | query | Base asset | Yes | string |
| counter | query | Counter asset | Yes | string |
| historicInterval | query | Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M] | Yes | string |
| indicatorInterval | query | Tecnichal's Indicator Interval [10,20,50,100,200,500,...] | Yes | integer |
| queryParameters | query | queryParameters | Yes |  |
| seriesType | query | Price's series type [open,close,high,low] | Yes | string |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

# /TECHNICAL/T3/**
## ***GET*** 

**Summary:** recuperarT3

### HTTP Request 
`***GET*** /technical/t3/**` 

**Parameters**

| Name | Located in | Description | Required | Type |
| ---- | ---------- | ----------- | -------- | ---- |
| base | query | Base asset | Yes | string |
| counter | query | Counter asset | Yes | string |
| historicInterval | query | Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M] | Yes | string |
| indicatorInterval | query | Tecnichal's Indicator Interval [10,20,50,100,200,500,...] | Yes | integer |
| queryParameters | query | queryParameters | Yes |  |
| seriesType | query | Price's series type [open,close,high,low] | Yes | string |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

# /TECHNICAL/TEMA/**
## ***GET*** 

**Summary:** recuperarTEMA

### HTTP Request 
`***GET*** /technical/tema/**` 

**Parameters**

| Name | Located in | Description | Required | Type |
| ---- | ---------- | ----------- | -------- | ---- |
| base | query | Base asset | Yes | string |
| counter | query | Counter asset | Yes | string |
| historicInterval | query | Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M] | Yes | string |
| indicatorInterval | query | Tecnichal's Indicator Interval [10,20,50,100,200,500,...] | Yes | integer |
| queryParameters | query | queryParameters | Yes |  |
| seriesType | query | Price's series type [open,close,high,low] | Yes | string |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

# /TECHNICAL/TMA/**
## ***GET*** 

**Summary:** recuperarTMA

### HTTP Request 
`***GET*** /technical/tma/**` 

**Parameters**

| Name | Located in | Description | Required | Type |
| ---- | ---------- | ----------- | -------- | ---- |
| base | query | Base asset | Yes | string |
| counter | query | Counter asset | Yes | string |
| historicInterval | query | Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M] | Yes | string |
| indicatorInterval | query | Tecnichal's Indicator Interval [10,20,50,100,200,500,...] | Yes | integer |
| queryParameters | query | queryParameters | Yes |  |
| seriesType | query | Price's series type [open,close,high,low] | Yes | string |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

# /TECHNICAL/WMA/**
## ***GET*** 

**Summary:** recuperarWMA

### HTTP Request 
`***GET*** /technical/wma/**` 

**Parameters**

| Name | Located in | Description | Required | Type |
| ---- | ---------- | ----------- | -------- | ---- |
| base | query | Base asset | Yes | string |
| counter | query | Counter asset | Yes | string |
| historicInterval | query | Historic Interval [1m,3m,15m,30m,1h,2h,4h,6h,8h,12h,1d,3d,1w,1M] | Yes | string |
| indicatorInterval | query | Tecnichal's Indicator Interval [10,20,50,100,200,500,...] | Yes | integer |
| queryParameters | query | queryParameters | Yes |  |
| seriesType | query | Price's series type [open,close,high,low] | Yes | string |

**Responses**

| Code | Description |
| ---- | ----------- |
| 200 | OK |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |

<!-- Converted with the swagger-to-slate https://github.com/lavkumarv/swagger-to-slate -->

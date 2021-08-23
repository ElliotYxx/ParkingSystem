import sys
from aip import AipOcr

APP_ID = '24002825'
API_KEY = 'GAt3Xnbt3TmRym4oTl8mWuh3'
SECRET_KEY = 'h2iVG7wM7KCeDZdOVR4KS0750Iy41ix6'

# 创建客户端对象
client = AipOcr(APP_ID, API_KEY, SECRET_KEY)
# 建立连接的超时时间，单位为毫秒
client.setConnectionTimeoutInMillis(5000)
# 通过打开的连接传输数据的超时时间
client.setSocketTimeoutInMillis(5000)

# 读取图片
def get_file(filePath):
    with open(filePath, 'rb') as fp:
        return fp.read()1

if __name__ == '__main__':
    filePath = sys.argv[1]
    img = get_file(filePath)
    res = client.licensePlate(img)
    print(res['words_result']['number'])
    print(res['words_result']['color'])

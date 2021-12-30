# Python蚁群算法解决TSP问题

Uses Ant Colony Optimization to solve the TSP.  
See http://en.wikipedia.org/wiki/Ant_colony_optimization_algorithms 

## 1. 运行环境

操作系统：Windows 10

IDE：PyCharm

Python：3.9.5

## 2. 代码说明

**anttsp.py** 是入口文件.  从 _citiesAndDistances.pickled 中读取二维 pickle 数组形式的数据，数据格式如下：

| CityName1 | CityName2 | ... | CitNameN |
|-----------|-----------|-----|----------|
| 0         | 23        | ... | 34       |
| 10        | 0         | ... | 22       |
| .         | .         | .   | .        |
| .         | .         | .   | .        |

从城市 A 到 B 的距离和从城市 B 到 A 的距离未必相等。

可以使用命令
```
$ python anttsp.py 14
```
运行该程序，第三个参数为城市数（即节点数），可以自定义。若没有输入第三个参数，则默认城市数为10。在给出的样例 pickled 文件中，包含14个城市的数据，如下表所示：

| Bergen | Hammerfest | Kirkenes | Kristiansand | Lillehammer | Oslo | Stavanger | Tromsø | Trondheim | Ålesund | Vinje | Flåm | Sogndal | Vang |
|--------|------------|----------|--------------|-------------|------|-----------|--------|-----------|---------|-------|------|---------|------|
| 0      | 2196       | 2258     | 439          | 440         | 496  | 207       | 1808   | 662       | 432     | 274   | 167  | 219     | 278  |
| 2170   | 0          | 480      | 2191         | 1813        | 1870 | 2404      | 377    | 1537      | 1834    | 2100  | 2034 | 1951    | 1918 |
| 2232   | 480        | 0        | 2222         | 1844        | 1901 | 2435      | 806    | 1598      | 1895    | 2131  | 2095 | 2012    | 1980 |
| 439    | 2193       | 2224     | 0            | 503         | 321  | 231       | 1962   | 816       | 820     | 266   | 487  | 584     | 521  |
| 440    | 1827       | 1848     | 505          | 0           | 184  | 546       | 1484   | 338       | 381     | 363   | 277  | 279     | 162  |
| 496    | 1872       | 1903     | 321          | 181         | 0    | 535       | 1640   | 495       | 562     | 230   | 333  | 333     | 236  |
| 207    | 2407       | 2437     | 231          | 546         | 534  | 0         | 1975   | 830       | 638     | 250   | 343  | 395     | 454  |
| 1782   | 378        | 806      | 1962         | 1484        | 1641 | 1962      | 0      | 1148      | 1445    | 1820  | 1645 | 1562    | 1530 |
| 636    | 1537       | 1599     | 817          | 339         | 496  | 816       | 1148   | 0         | 300     | 674   | 500  | 417     | 384  |
| 432    | 1834       | 1896     | 820          | 381         | 563  | 638       | 1445   | 300       | 0       | 601   | 347  | 275     | 355  |
| 275    | 2103       | 2134     | 244          | 363         | 230  | 279       | 1821   | 675       | 601     | 0     | 275  | 327     | 348  |
| 167    | 2033       | 2095     | 517          | 277         | 333  | 348       | 1645   | 499       | 347     | 275   | 0    | 72      | 115  |
| 219    | 1951       | 2012     | 585          | 279         | 334  | 400       | 1562   | 417       | 275     | 327   | 72   | 0       | 117  |
| 278    | 1918       | 1980     | 521          | 162         | 237  | 459       | 1529   | 384       | 355     | 347   | 115  | 117     | 0    |

若城市数小于等于10，则设置蚂蚁数（num_ants）为20，迭代轮数（num_iterations）为12；若大于等于10，则蚂蚁数为28，迭代轮数为20。

&nbsp;

**antgraph.py** 包含AntGraph类，用于描述和更新图。 delta_mat 为记录城市距离的邻接矩阵， tau_mat 为记录路径上信息素量的矩阵。

&nbsp;

**antcolony.py** 包含AntColony类，模拟蚁群的行为来更新图中的信息素量，每轮迭代全部更新一次。在 start 函数中，执行若干轮的迭代，每一次迭代让所有蚂蚁各自对图进行一次完整的符合 TSP 要求的遍历，每只蚂蚁在遍历完成之后将路径和总开销反馈给蚁群，然后蚁群对全图的信息素量按以下公式进行一次更新：
# Python蚁群算法解决TSP问题

Uses Ant Colony Optimization to solve the TSP.  
See http://en.wikipedia.org/wiki/Ant_colony_optimization_algorithms 

## 1. 运行环境

操作系统：Windows 10

IDE：PyCharm

Python：3.9.5

## 2. 代码说明

**anttsp.py** 是入口文件.  从 _citiesAndDistances.pickled 中读取二维 pickle 数组形式的数据，数据格式如下：

| CityName1 | CityName2 | ... | CitNameN |
|-----------|-----------|-----|----------|
| 0         | 23        | ... | 34       |
| 10        | 0         | ... | 22       |
| .         | .         | .   | .        |
| .         | .         | .   | .        |

从城市 A 到 B 的距离和从城市 B 到 A 的距离未必相等。

可以使用命令
```
$ python anttsp.py 14
```
运行该程序，第三个参数为城市数（即节点数），可以自定义。若没有输入第三个参数，则默认城市数为10。在给出的样例 pickled 文件中，包含14个城市的数据，如下表所示：

| Bergen | Hammerfest | Kirkenes | Kristiansand | Lillehammer | Oslo | Stavanger | Tromsø | Trondheim | Ålesund | Vinje | Flåm | Sogndal | Vang |
|--------|------------|----------|--------------|-------------|------|-----------|--------|-----------|---------|-------|------|---------|------|
| 0      | 2196       | 2258     | 439          | 440         | 496  | 207       | 1808   | 662       | 432     | 274   | 167  | 219     | 278  |
| 2170   | 0          | 480      | 2191         | 1813        | 1870 | 2404      | 377    | 1537      | 1834    | 2100  | 2034 | 1951    | 1918 |
| 2232   | 480        | 0        | 2222         | 1844        | 1901 | 2435      | 806    | 1598      | 1895    | 2131  | 2095 | 2012    | 1980 |
| 439    | 2193       | 2224     | 0            | 503         | 321  | 231       | 1962   | 816       | 820     | 266   | 487  | 584     | 521  |
| 440    | 1827       | 1848     | 505          | 0           | 184  | 546       | 1484   | 338       | 381     | 363   | 277  | 279     | 162  |
| 496    | 1872       | 1903     | 321          | 181         | 0    | 535       | 1640   | 495       | 562     | 230   | 333  | 333     | 236  |
| 207    | 2407       | 2437     | 231          | 546         | 534  | 0         | 1975   | 830       | 638     | 250   | 343  | 395     | 454  |
| 1782   | 378        | 806      | 1962         | 1484        | 1641 | 1962      | 0      | 1148      | 1445    | 1820  | 1645 | 1562    | 1530 |
| 636    | 1537       | 1599     | 817          | 339         | 496  | 816       | 1148   | 0         | 300     | 674   | 500  | 417     | 384  |
| 432    | 1834       | 1896     | 820          | 381         | 563  | 638       | 1445   | 300       | 0       | 601   | 347  | 275     | 355  |
| 275    | 2103       | 2134     | 244          | 363         | 230  | 279       | 1821   | 675       | 601     | 0     | 275  | 327     | 348  |
| 167    | 2033       | 2095     | 517          | 277         | 333  | 348       | 1645   | 499       | 347     | 275   | 0    | 72      | 115  |
| 219    | 1951       | 2012     | 585          | 279         | 334  | 400       | 1562   | 417       | 275     | 327   | 72   | 0       | 117  |
| 278    | 1918       | 1980     | 521          | 162         | 237  | 459       | 1529   | 384       | 355     | 347   | 115  | 117     | 0    |

若城市数小于等于10，则设置蚂蚁数（num_ants）为20，迭代轮数（num_iterations）为12；若大于等于10，则蚂蚁数为28，迭代轮数为20。

&nbsp;

**antgraph.py** 包含AntGraph类，用于描述和更新图。 delta_mat 为记录城市距离的邻接矩阵， tau_mat 为记录路径上信息素量的矩阵。

&nbsp;

**antcolony.py** 包含AntColony类，模拟蚁群的行为来更新图中的信息素量，每轮迭代全部更新一次。在 start 函数中，执行若干轮的迭代，每一次迭代让所有蚂蚁各自对图进行一次完整的符合 TSP 要求的遍历，每只蚂蚁在遍历完成之后将路径和总开销反馈给蚁群，然后蚁群对全图的信息素量按以下公式进行一次更新：

![](<img src="https://latex.codecogs.com/svg.image?\tau&space;_{rs}\leftarrow&space;\left&space;(&space;1-\alpha&space;&space;\right&space;)\tau&space;_{rs}&plus;\tau&space;_{rs}^k" title="\tau _{rs}\leftarrow \left ( 1-\alpha \right )\tau _{rs}+\tau _{rs}^k" />)

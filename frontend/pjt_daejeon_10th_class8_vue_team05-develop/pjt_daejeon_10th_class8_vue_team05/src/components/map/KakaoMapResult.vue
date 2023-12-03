<script setup>
import { onMounted, queuePostFlushCb, watch } from "vue";
import axios from "axios";

const props = defineProps({
  locations: Array,
});

var polyline;
// var bounds = new kakao.maps.LatLngBounds();

function drawLine(day, bounds) {
  //라인 그리기
  if (props.locations.length < 1) return;

  if (props.locations[day].length > 1) {
    const url = "https://apis-navi.kakaomobility.com/v1/waypoints/directions";
    const headers = {
      "Content-Type": "application/json",
      Authorization: "KakaoAK 48fa87849868adf9680aecff268f4c29",
    };
    const data = {
      origin: {
        // "y": props.locations[1][0].lon,
      },
      destination: {
        // "x": props.locations[1][props.locations[1].length-1].lat,
        // "y": props.locations[1][props.locations[1].length-1].lon
      },
      waypoints: [
        // {
        //     "name": "name0",
        //     "x": 127.11341936045922,
        //     "y": 37.39639094915999
        // }
      ],
      priority: "RECOMMEND",
      car_fuel: "GASOLINE",
      car_hipass: false,
      alternatives: true,
      road_details: false,
      avoid: ["roadevent"],
    };

    if (props.locations[day].length > 1) {
      data.origin.x = props.locations[day][0].x;
      data.origin.y = props.locations[day][0].y;

      data.destination.x = props.locations[day][props.locations[day].length - 1].x;
      data.destination.y = props.locations[day][props.locations[day].length - 1].y;

      for (let i = 1; i < props.locations[day].length - 1; i++) {
        data.waypoints.push({
          name: "waypoint" + i,
          x: props.locations[day][i].x,
          y: props.locations[day][i].y,
        });
      }

      axios
        .post(url, data, {
          headers,
        })
        .then((response) => {
          let linePath = [];
          for (let i = 0; i < response.data.routes[0].sections.length; i++) {
            response.data.routes[0].sections[i].roads.forEach((router) => {
              router.vertexes.forEach((vertex, index) => {
                // x,y 좌표가 우르르 들어옵니다. 그래서 인덱스가 짝수일 때만 linePath에 넣어봅시다.
                // 저도 실수한 것인데 lat이 y이고 lng이 x입니다.
                if (index % 2 === 0) {
                  linePath.push(
                    new kakao.maps.LatLng(router.vertexes[index + 1], router.vertexes[index])
                  );
                }
              });
            });
          }
          // console.log(linePath)

          let colorArray = [
            "#FF6633",
            "#FFB399",
            "#FF33FF",
            "#FFFF99",
            "#00B3E6",
            "#E6B333",
            "#3366E6",
            "#999966",
            "#99FF99",
            "#B34D4D",
            "#80B300",
            "#809900",
            "#E6B3B3",
            "#6680B3",
            "#66991A",
            "#FF99E6",
            "#CCFF1A",
            "#FF1A66",
            "#E6331A",
            "#33FFCC",
            "#66994D",
            "#B366CC",
            "#4D8000",
            "#B33300",
            "#CC80CC",
            "#66664D",
            "#991AFF",
            "#E666FF",
            "#4DB3FF",
            "#1AB399",
            "#E666B3",
            "#33991A",
            "#CC9999",
            "#B3B31A",
            "#00E680",
            "#4D8066",
            "#809980",
            "#E6FF80",
            "#1AFF33",
            "#999933",
            "#FF3380",
            "#CCCC00",
            "#66E64D",
            "#4D80CC",
            "#9900B3",
            "#E64D66",
            "#4DB380",
            "#FF4D4D",
            "#99E6E6",
            "#6666FF",
          ];

          polyline && polyline[day] ? polyline[day].setMap(null) : true;

          polyline[day] = new kakao.maps.Polyline({
            endArrow: true,
            path: linePath,
            strokeWeight: 5,
            strokeColor: colorArray[Math.round(Math.random() * colorArray.length)], //'#' + Math.floor(Math.random() * 60).toString(16) + Math.floor(Math.random() * 60).toString(16) + Math.floor(Math.random() * 60).toString(16),
            strokeOpacity: 1,
            strokeStyle: "shortdash",
          });

          polyline[day].setMap(map);
        });
    }
  }

  // 마커 이미지의 이미지 주소입니다
  // var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png';
  let markerCount = 0;
  for (let j = 1; j < day; j++) {
    markerCount += props.locations[j].length;
  }

  for (var i = 0; i < props.locations[day].length; i++) {
    // 마커 이미지의 이미지 크기 입니다
    // var imageSize = new kakao.maps.Size(24, 35);
    //
    // 마커 이미지를 생성합니다
    // var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
    let position = new kakao.maps.LatLng(props.locations[day][i].y, props.locations[day][i].x);
    bounds.extend(position);

    var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
      imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
      imgOptions = {
        spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
        spriteOrigin: new kakao.maps.Point(0, (markerCount + i) * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
        offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
      },
      markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
      marker = new kakao.maps.Marker({
        position: position, // 마커의 위치
        image: markerImage,
      });
    // 마커를 생성합니다
    // var marker = new kakao.maps.Marker({
    //   map: map, // 마커를 표시할 지도
    //   position: position, // 마커를 표시할 위치
    //   title: "title" + i, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
    //   image: markerImage, // 마커 이미지
    // });

    marker.setMap(map);

    var content =
      '<div class="overlaybox">' +
      '    <div class="boxtitle">금주 영화순위</div>' +
      '    <div class="first">' +
      '        <div class="triangle text">1</div>' +
      '        <div class="movietitle text">드래곤 길들이기2</div>' +
      "    </div>" +
      "    <ul>" +
      '        <li class="up">' +
      '            <span class="number">2</span>' +
      '            <span class="title">명량</span>' +
      '            <span class="arrow up"></span>' +
      '            <span class="count">2</span>' +
      "        </li>" +
      "        <li>" +
      '            <span class="number">3</span>' +
      '            <span class="title">해적(바다로 간 산적)</span>' +
      '            <span class="arrow up"></span>' +
      '            <span class="count">6</span>' +
      "        </li>" +
      "        <li>" +
      '            <span class="number">4</span>' +
      '            <span class="title">해무</span>' +
      '            <span class="arrow up"></span>' +
      '            <span class="count">3</span>' +
      "        </li>" +
      "        <li>" +
      '            <span class="number">5</span>' +
      '            <span class="title">안녕, 헤이즐</span>' +
      '            <span class="arrow down"></span>' +
      '            <span class="count">1</span>' +
      "        </li>" +
      "    </ul>" +
      "</div>";

    // 커스텀 오버레이를 생성합니다
    var customOverlay = new kakao.maps.CustomOverlay({
      position: position,
      content: content,
      xAnchor: 0.3,
      yAnchor: 0.91,
    });

    // 커스텀 오버레이를 지도에 표시합니다
    // customOverlay.setMap(map);
  }

  return bounds;
}

watch(props.locations, () => {
  let bounds = new kakao.maps.LatLngBounds();

  for (let i = 1; i < props.locations.length; i++) {
    bounds = drawLine(i, bounds);
  }

  map.setBounds(bounds);
  // console.log("change data")
});

var map;
// var ps;
// var infowindow
// var positions = [];
// var markers = [];
// var data;

onMounted(() => {
  polyline = new Array(props.locations.length);
  // console.log("locations" + props.locations)

  // console.log(props.day)
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${
      import.meta.env.VITE_KAKAO_API_KEY
    }&libraries=services,clusterer`;
    /* global kakao */
    script.onload = () => kakao.maps.load(() => initMap());
    document.head.appendChild(script);
  }
});

const initMap = () => {
  const container = document.getElementById("mapResult");
  // console.log(props.locations.length)
  const options = {
    center: props.locations
      ? new kakao.maps.LatLng(33.450701, 126.570667)
      : new kakao.maps.LatLng(props.locations[1][0].y, props.locations[1][0].x),
    level: 3,
  };
  map = new kakao.maps.Map(container, options);
  // 장소 검색 객체를 생성합니다
  //   ps = new kakao.maps.services.Places();

  // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
  //   infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
  map.relayout();

  // console.log("initMap")

  let bounds = new kakao.maps.LatLngBounds();

  for (let i = 1; i < props.locations.length; i++) {
    drawLine(i, bounds);
  }

  if (!bounds.isEmpty()) map.setBounds(bounds);
};
</script>

<template>
  <div class="map_wrap">
    <div id="mapResult"></div>
  </div>
</template>

<style>
#mapResult {
  position: relative;
  width: 100%;
  height: 90%;
}

.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
  color: #000;
  text-decoration: none;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 700px;
}

.button-class {
  border: 0;
  background-color: transparent;
}

.overlaybox {
  position: relative;
  width: 360px;
  height: 350px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/box_movie.png")
    no-repeat;
  padding: 15px 10px;
}
.overlaybox div,
ul {
  overflow: hidden;
  margin: 0;
  padding: 0;
}
.overlaybox li {
  list-style: none;
}
.overlaybox .boxtitle {
  color: #fff;
  font-size: 16px;
  font-weight: bold;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png")
    no-repeat right 120px center;
  margin-bottom: 8px;
}
.overlaybox .first {
  position: relative;
  width: 247px;
  height: 136px;
  background: url("https://img1.kakaocdn.net/cthumb/local/C320x320/?fname=http%3A%2F%2Ft1.kakaocdn.net%2Ffiy_reboot%2Fplace%2F68012AA6839C43E7A5007B7E34993407")
    no-repeat;
  margin-bottom: 8px;
}
.first .text {
  color: #fff;
  font-weight: bold;
}
.first .triangle {
  position: absolute;
  width: 48px;
  height: 48px;
  top: 0;
  left: 0;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/triangle.png") no-repeat;
  padding: 6px;
  font-size: 18px;
}
.first .movietitle {
  position: absolute;
  width: 100%;
  bottom: 0;
  background: rgba(0, 0, 0, 0.4);
  padding: 7px 15px;
  font-size: 14px;
}
.overlaybox ul {
  width: 247px;
}
.overlaybox li {
  position: relative;
  margin-bottom: 2px;
  background: #2b2d36;
  padding: 5px 10px;
  color: #aaabaf;
  line-height: 1;
}
.overlaybox li span {
  display: inline-block;
}
.overlaybox li .number {
  font-size: 16px;
  font-weight: bold;
}
.overlaybox li .title {
  font-size: 13px;
}
.overlaybox ul .arrow {
  position: absolute;
  margin-top: 8px;
  right: 25px;
  width: 5px;
  height: 3px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/updown.png") no-repeat;
}
.overlaybox li .up {
  background-position: 0 -40px;
}
.overlaybox li .down {
  background-position: 0 -60px;
}
.overlaybox li .count {
  position: absolute;
  margin-top: 5px;
  right: 15px;
  font-size: 10px;
}
.overlaybox li:hover {
  color: #fff;
  background: #d24545;
}
.overlaybox li:hover .up {
  background-position: 0 0px;
}
.overlaybox li:hover .down {
  background-position: 0 -20px;
}
</style>



function setup(){
  createCanvas(710, 400, WEBGL);
}

function draw(){
  background(250);
  
  translate(-240, 100, 0);
  normalMaterial();
  push();
  // rotateZ(frameCount * 0.01);
  // rotateX(frameCount * 0.01);
  // rotateY(frameCount * 0.01);
  box(20, 70, 70);
  pop();
}
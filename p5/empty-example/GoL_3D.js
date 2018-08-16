
var output;
var cells;
var iterations;
var world_dimensions;
var cell_dimensions;
var time_checkpoint;
var current_iteration = 0;
var canvasX = 700;
var canvasY = 700;
var time_between_frames = 200;
var centersOfMass;
var radiuses;

function preload() {
    output = loadStrings('output.txt');
}

function setup() {
    createCanvas(canvasX, canvasY, WEBGL);

    iterations = output[0];

    world_dimensions = output[1].split(" ");
    world_dimensions = {x: parseInt(world_dimensions[0]), y: parseInt(world_dimensions[1]), z: parseInt(world_dimensions[2])};

    var side = min(canvasX, canvasY)/2;
    cell_dimensions = {x: side/world_dimensions.x, y: side/world_dimensions.y, z: side/world_dimensions.z};


    cells = new Array(parseInt(iterations) + 1);
    centersOfMass = new Array(parseInt(iterations) + 1);
    radiuses = new Array(parseInt(iterations) + 1);
    for (var i = 0; i <= iterations; i++) {
        cells[i] = new Array(world_dimensions.z);
        centersOfMass[i] = {x: parseInt(output[i * (world_dimensions.y+1) + 2].split(" ")[0]),
                            y: parseInt(output[i * (world_dimensions.y+1) + 2].split(" ")[1]),
                            z: parseInt(output[i * (world_dimensions.y+1) + 2].split(" ")[2])};
        radiuses[i] = parseInt(output[i * (world_dimensions.y+1) + 2].split(" ")[3]);
        for (var z = 0; z < world_dimensions.z; z++) {
            cells[i][z] = new Array(world_dimensions.y);
            for (var y = 0; y < world_dimensions.y; y++) {
                cells[i][z][y] = new Array(world_dimensions.x);
                for (var x = 0; x < world_dimensions.x; x++) {
                    cells[i][z][y][x] = output[(z + i*world_dimensions.z) * world_dimensions.y + y + i + 3].charAt(x)
                }
            }
        }
    }
    time_checkpoint = millis();
    console.log(cells)
    console.log(centersOfMass)
    console.log(radiuses)
}


function draw() {
    background(200);
    // translate(-(canvasX - cell_dimensions.x) / 2, -(canvasY - cell_dimensions.y) / 2, 0);
    // translate(0, 0, -mouseX);
    // rotateX(mouseY * 0.01);
    // rotateY(mouseY * 0.01);
    rotateX(45);
    rotateZ(45);
    for (var z = 0; z < world_dimensions.z; z++) {
        for (var y = 0; y < world_dimensions.y; y++) {
            for (var x = 0; x < world_dimensions.x; x++) {
                // rotateX(frameCount * 0.01);
                // rotateY(frameCount * 0.01);
                // console.log(x + " " + y + " " + z + cells[current_iteration][0][y][x])
                drawCell(x, y, z, cells[current_iteration][z][y][x])
            }
        }
    }
    if (millis() - time_checkpoint > time_between_frames) {
        current_iteration = (current_iteration + 1) % (parseInt(iterations) + 1);
        time_checkpoint = millis();
    }
}

function drawCell(x, y, z, state) {
    if (state == "O") {
        var size = min(min(cell_dimensions.x, cell_dimensions.y), cell_dimensions.z);
        // noStroke();
        // translate(x - world_dimensions.x/2, y - world_dimensions.y/2, z - world_dimensions.z/2);
        translate(x * size, y * size, z * size);
        box(size);
        translate(-x * size, -y * size, -z * size);
    }
}

var output;
var cells;
var iterations;
var world_dimensions;
var cell_dimensions;
var time_checkpoint;
var current_iteration = 0;
var canvasX = 700;
var canvasY = 700;
var time_between_frames = 100;

function preload() {
    output = loadStrings('output.txt');
}

function setup() {
    createCanvas(canvasX, canvasY);

    iterations = output[0];

    world_dimensions = output[1].split(" ");
    world_dimensions = {x: world_dimensions[0], y: world_dimensions[1], z: world_dimensions[2]};

    cell_dimensions = {x: canvasX/world_dimensions.x, y: canvasY/world_dimensions.y};
    var side = min(cell_dimensions.x, cell_dimensions.y);
    cell_dimensions = {x: side, y: side};


    cells = new Array(parseInt(iterations) + 1);
    for (var i = 0; i <= iterations; i++) {
        cells[i] = new Array(world_dimensions.z);
        for (var z = 0; z < world_dimensions.z; z++) {
            cells[i][z] = new Array(world_dimensions.y);
            for (var y = 0; y < world_dimensions.y; y++) {
                cells[i][z][y] = new Array(world_dimensions.x);
                for (var x = 0; x < world_dimensions.x; x++) {
                    cells[i][z][y][x] = output[(z + i) * world_dimensions.y + y + 2].charAt(x)
                }
            }
        }
    }

    time_checkpoint = millis();
}

function draw() {
    background(200);

    for (var y = 0; y < world_dimensions.y; y++) {
        for (var x = 0; x < world_dimensions.x; x++) {
            drawCell(x * cell_dimensions.x, y * cell_dimensions.y, cells[current_iteration][0][y][x])
        }
    }

    if (millis() - time_checkpoint > time_between_frames) {
        current_iteration = (current_iteration + 1) % (parseInt(iterations) + 1);
        time_checkpoint = millis();
    }
}

function drawCell(x, y, state) {
    if (state == "O") {
        rect(x, y, cell_dimensions.x, cell_dimensions.y);
    }
}
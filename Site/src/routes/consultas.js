var express = require("express");
var router = express.Router();

var consultaController = require("../controllers/consultaController");

router.get("/", function (req, res) {
    consultaController.testar(req, res);
});

module.exports = router;
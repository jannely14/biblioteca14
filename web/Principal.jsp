<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>

<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-3">
            <form action="Controlador?menu=NuevaVenta" method="POST" class="text-center">
                <div class="card">
                    <div class="card-body">
                        <img src="img/cart.png" alt="" class="mb-3" style="width: 50px; height: 50px;">
                        <h5 class="card-title">VENTA NUEVA</h5>
                        <button type="submit" name="accion" value="ventanueva" class="btn btn-lg btn-primary">Abrir</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-3">
            <form action="" method="POST" class="text-center">
                <div class="card">
                    <div class="card-body">
                        <img src="img/caja.png" alt="" class="mb-3" style="width: 50px; height: 50px;">
                        <h5 class="card-title">CIERRE CAJA</h5>
                        <button type="submit" value="" class="btn btn-lg btn-secondary">Abrir</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-3">
            <form action="" method="POST" class="text-center">
                <div class="card">
                    <div class="card-body">
                        <img src="img/informe.png" alt="" class="mb-3" style="width: 50px; height: 50px;">
                        <h5 class="card-title">REPORTES</h5>
                        <button type="submit" value="" class="btn btn-lg btn-info">Abrir</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-3">
            <form action="" method="POST" class="text-center">
                <div class="card">
                    <div class="card-body">
                        <img src="img/setting.png" alt="" class="mb-3" style="width: 50px; height: 50px;">
                        <h5 class="card-title">OTROS</h5>
                        <button type="submit" value="" class="btn btn-lg btn-warning">Abrir</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="commons/footer.jspf"%>

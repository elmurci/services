package app.keyconnect.server.controllers;

import app.keyconnect.server.gateways.exceptions.UnknownNetworkException;
import app.keyconnect.api.client.model.BlockchainAccountTransaction;
import app.keyconnect.api.client.model.SubmitTransactionRequest;
import app.keyconnect.api.client.model.SubmitTransactionResult;
import app.keyconnect.server.factories.BlockchainGatewayFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlockchainTransactionController {

  private final BlockchainGatewayFactory blockchainGatewayFactory;

  public BlockchainTransactionController(
      BlockchainGatewayFactory blockchainGatewayFactory) {
    this.blockchainGatewayFactory = blockchainGatewayFactory;
  }

  @GetMapping(
      path = "/v1/blockchains/{chainId}/transactions/{hash}",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<BlockchainAccountTransaction> getTransaction(
      @PathVariable("chainId") String chainId,
      @PathVariable("hash") String hash,
      @RequestParam(value = "network", required = false, defaultValue = "mainnet") String network
  ) throws UnknownNetworkException {
    return ResponseEntity.ok(
        blockchainGatewayFactory.getGateway(chainId).getTransaction(network, hash)
    );
  }

  @PostMapping(
      path = "/v1/blockchains/{chainId}/transactions",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<SubmitTransactionResult> submitTransaction(
      @PathVariable("chainId") String chainId,
      @RequestParam(value = "network", required = false, defaultValue = "mainnet") String network,
      @RequestBody SubmitTransactionRequest submitTransactionRequest
  ) throws UnknownNetworkException {
    return ResponseEntity.accepted().body(
        blockchainGatewayFactory.getGateway(chainId).submitTransaction(network, submitTransactionRequest)
    );
  }

}

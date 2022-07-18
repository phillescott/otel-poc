docker run \
-v "${PWD}/otel-local-config.yaml":/otel-local-config.yaml \
-p 4317:4317 \
otel/opentelemetry-collector \
--config otel-local-config.yaml;